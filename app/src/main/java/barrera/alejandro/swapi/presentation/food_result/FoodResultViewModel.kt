package barrera.alejandro.swapi.presentation.food_result

import androidx.annotation.MainThread
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import barrera.alejandro.swapi.domain.model.Food
import barrera.alejandro.swapi.domain.use_case.GetEquivalentFoods
import barrera.alejandro.swapi.domain.use_case.GetFoodById
import barrera.alejandro.swapi.domain.use_case.GetFoodEquivalenceCount
import barrera.alejandro.swapi.domain.use_case.GetFoodsByCategoryId
import barrera.alejandro.swapi.domain.use_case.IncrementFoodEquivalenceCount
import barrera.alejandro.swapi.domain.use_case.ResetFoodEquivalenceCount
import barrera.alejandro.swapi.presentation.food_result.FoodResultContract.Action
import barrera.alejandro.swapi.presentation.food_result.FoodResultContract.AdState
import barrera.alejandro.swapi.presentation.food_result.FoodResultContract.State
import barrera.alejandro.swapi.presentation.mapper.toFoodUi
import barrera.alejandro.swapi.presentation.navigation.FoodResult
import barrera.alejandro.swapi.util.constant.WHILE_SUBSCRIBED_STOP_TIMEOUT_MILLIS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getFoodById: GetFoodById,
    private val getFoodsByCategoryId: GetFoodsByCategoryId,
    private val getEquivalentFoods: GetEquivalentFoods,
    private val getFoodEquivalenceCount: GetFoodEquivalenceCount,
    private val incrementFoodEquivalenceCount: IncrementFoodEquivalenceCount,
    private val resetFoodEquivalenceCount: ResetFoodEquivalenceCount,
) : ViewModel() {

    private val route = savedStateHandle.toRoute<FoodResult>()

    private val foodId = route.foodId
    private val discardedFoodAmount = route.amount
    private val discardedFoodAmountValue = discardedFoodAmount.toDouble()

    private val adState = MutableStateFlow<AdState>(AdState.Checking)

    private var initialized = false

    @OptIn(ExperimentalCoroutinesApi::class)
    private val resultState: StateFlow<State> = getFoodById(foodId)
        .flatMapLatest { discardedFood ->
            getEquivalentFoodState(
                discardedFood = discardedFood,
                discardedFoodAmountValue = discardedFoodAmountValue,
            )
        }
        .catch {
            emit(State.Failure)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(
                stopTimeoutMillis = WHILE_SUBSCRIBED_STOP_TIMEOUT_MILLIS,
            ),
            initialValue = State.Loading,
        )

    private fun getEquivalentFoodState(
        discardedFood: Food,
        discardedFoodAmountValue: Double,
    ): Flow<State> = getFoodsByCategoryId(discardedFood.category.id)
        .map<List<Food>, State> { replacementFoods ->
            State.Success(
                discardedFood = discardedFood.toFoodUi(),
                discardedFoodAmount = this.discardedFoodAmount,
                equivalentFoods = getEquivalentFoods(
                    discardedFood = discardedFood,
                    discardedFoodAmount = discardedFoodAmountValue,
                    replacementFoods = replacementFoods,
                ).map { food ->
                    food.toFoodUi()
                },
            )
        }

    val state: StateFlow<State> = combine(
        resultState,
        adState,
    ) { resultState, adState ->
        when (resultState) {
            State.Loading -> State.Loading
            State.Failure -> State.Failure
            is State.Success -> resultState.copy(adState = adState)
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(
                stopTimeoutMillis = WHILE_SUBSCRIBED_STOP_TIMEOUT_MILLIS,
            ),
            initialValue = State.Loading,
        )

    @MainThread
    fun initialize() {
        if (initialized) return

        initialized = true

        viewModelScope.launch {
            val result = resultState.first { state ->
                state != State.Loading
            }

            if (result is State.Success) {
                checkAdRequirement()
            }
        }
    }

    fun onAction(action: Action) {
        when (action) {
            Action.AdFinished -> adState.value = AdState.Completed
        }
    }

    private suspend fun checkAdRequirement() {
        val equivalenceCount = getFoodEquivalenceCount()
            .catch {
                emit(DEFAULT_FOOD_EQUIVALENCE_COUNT)
            }
            .first()

        if (equivalenceCount == MAXIMUM_FOOD_EQUIVALENCE_COUNT) {
            resetFoodEquivalenceCount()
            adState.value = AdState.Required
        } else {
            incrementFoodEquivalenceCount()
            adState.value = AdState.Completed
        }
    }

    private companion object {
        const val DEFAULT_FOOD_EQUIVALENCE_COUNT = 0
        const val MAXIMUM_FOOD_EQUIVALENCE_COUNT = 5
    }
}