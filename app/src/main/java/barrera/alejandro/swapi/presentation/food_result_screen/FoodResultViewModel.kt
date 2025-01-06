package barrera.alejandro.swapi.presentation.food_result_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import barrera.alejandro.swapi.presentation.base.BaseViewModel
import barrera.alejandro.swapi.presentation.navigation.FoodResult
import barrera.alejandro.swapi.util.annotation.GetEquivalentFoodsUseCase
import barrera.alejandro.swapi.util.annotation.GetFoodByIdUseCase
import barrera.alejandro.swapi.util.annotation.GetFoodsByCategoryIdUseCase
import barrera.alejandro.swapi.domain.model.Food
import barrera.alejandro.swapi.domain.use_case.FlowUseCaseNoParams
import barrera.alejandro.swapi.domain.use_case.GetEquivalentFoods
import barrera.alejandro.swapi.domain.use_case.GetFoodById
import barrera.alejandro.swapi.domain.use_case.GetFoodsByCategoryId
import barrera.alejandro.swapi.domain.use_case.SuspendUseCase
import barrera.alejandro.swapi.domain.use_case.SuspendUseCaseNoParamsNoResponse
import barrera.alejandro.swapi.domain.use_case.UseCase
import barrera.alejandro.swapi.presentation.base.UiEvent
import barrera.alejandro.swapi.presentation.mapper.toFoodUi
import barrera.alejandro.swapi.util.annotation.GetFoodEquivalenceCountUseCase
import barrera.alejandro.swapi.util.annotation.IncrementFoodEquivalenceCountUseCase
import barrera.alejandro.swapi.util.annotation.ResetFoodEquivalenceCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodResultViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    @GetFoodByIdUseCase
    private val getFoodById: SuspendUseCase<GetFoodById.Params, Food>,
    @GetFoodsByCategoryIdUseCase
    private val getFoodsByCategoryId: SuspendUseCase<GetFoodsByCategoryId.Params, List<Food>>,
    @GetEquivalentFoodsUseCase
    private val getEquivalentFoods: UseCase<GetEquivalentFoods.Params, List<Food>>,
    @GetFoodEquivalenceCountUseCase
    private val getFoodEquivalenceCount: FlowUseCaseNoParams<Int>,
    @IncrementFoodEquivalenceCountUseCase
    private val incrementFoodEquivalenceCount: SuspendUseCaseNoParamsNoResponse,
    @ResetFoodEquivalenceCountUseCase
    private val resetFoodEquivalenceCount: SuspendUseCaseNoParamsNoResponse
) : BaseViewModel<FoodResultScreenState, FoodResultScreenEvent>(
    initialState = FoodResultScreenState.Loading
) {

    init {
        onEvent(FoodResultScreenEvent.ShowAdIfNeeded)
    }

    override fun onEvent(event: FoodResultScreenEvent) {
        when (event) {
            is FoodResultScreenEvent.ShowAdIfNeeded -> showAdIfNeeded()
            is FoodResultScreenEvent.LoadEquivalentFood -> loadEquivalentFood()
        }
    }

    private fun showAdIfNeeded() {
        viewModelScope.launch {
            val foodEquivalenceCount = getFoodEquivalenceCount()
                .catch {
                    onEvent(FoodResultScreenEvent.LoadEquivalentFood)
                    emit(DEFAULT_FOOD_EQUIVALENCE_COUNT)
                }
                .first()

            if (foodEquivalenceCount == MAXIMUM_FOOD_EQUIVALENCE_COUNT) {
                resetFoodEquivalenceCount()
                sendUiEvent(
                    UiEvent.ShowAd(
                        onAdDismissed = {
                            onEvent(FoodResultScreenEvent.LoadEquivalentFood)
                        }
                    )
                )
            } else {
                incrementFoodEquivalenceCount()
                onEvent(FoodResultScreenEvent.LoadEquivalentFood)
            }
        }
    }

    private fun incrementFoodEquivalenceCount() {
        viewModelScope.launch {
            incrementFoodEquivalenceCount.invoke()
        }
    }

    private fun resetFoodEquivalenceCount() {
        viewModelScope.launch {
            resetFoodEquivalenceCount.invoke()
        }
    }

    private fun loadEquivalentFood() {
        viewModelScope.launch {
            getFoodById(
                params = GetFoodById.Params(id = savedStateHandle.toRoute<FoodResult>().foodId)
            ).fold(
                success = { discardedFood ->
                    handleReplacementFoods(
                        discardedFood = discardedFood,
                        discardedFoodAmount = savedStateHandle.toRoute<FoodResult>().amount
                    )
                },
                failure = {
                    state = FoodResultScreenState.Failure
                }
            )
        }
    }

    private suspend fun handleReplacementFoods(
        discardedFood: Food,
        discardedFoodAmount: String
    ) {
        getFoodsByCategoryId(
            params = GetFoodsByCategoryId.Params(discardedFood.category.id)
        ).fold(
            success = { replacementFoods ->
                state = FoodResultScreenState.Success(
                    discardedFood = discardedFood.toFoodUi(),
                    discardedFoodAmount = discardedFoodAmount,
                    equivalentFoods = getEquivalentFoods(
                        GetEquivalentFoods.Params(
                            discardedFood = discardedFood,
                            discardedFoodAmount = discardedFoodAmount
                                .replace(oldValue = ",", newValue = ".")
                                .toDouble(),
                            replacementFoods = replacementFoods
                        )
                    ).map { food -> food.toFoodUi() }
                )
            },
            failure = {
                state = FoodResultScreenState.Failure
            }
        )
    }

    companion object {
        private const val DEFAULT_FOOD_EQUIVALENCE_COUNT = 0
        private const val MAXIMUM_FOOD_EQUIVALENCE_COUNT = 5
    }
}