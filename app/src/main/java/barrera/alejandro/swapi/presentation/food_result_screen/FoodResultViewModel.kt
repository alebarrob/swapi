package barrera.alejandro.swapi.presentation.food_result_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import barrera.alejandro.swapi.domain.model.Food
import barrera.alejandro.swapi.domain.use_case.GetEquivalentFoods
import barrera.alejandro.swapi.domain.use_case.GetFoodById
import barrera.alejandro.swapi.domain.use_case.GetFoodEquivalenceCount
import barrera.alejandro.swapi.domain.use_case.GetFoodsByCategoryId
import barrera.alejandro.swapi.domain.use_case.IncrementFoodEquivalenceCount
import barrera.alejandro.swapi.domain.use_case.ResetFoodEquivalenceCount
import barrera.alejandro.swapi.presentation.base.BaseViewModel
import barrera.alejandro.swapi.presentation.base.UiEvent
import barrera.alejandro.swapi.presentation.mapper.toFoodUi
import barrera.alejandro.swapi.presentation.navigation.FoodResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodResultViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getFoodById: GetFoodById,
    private val getFoodsByCategoryId: GetFoodsByCategoryId,
    private val getEquivalentFoods: GetEquivalentFoods,
    private val getFoodEquivalenceCount: GetFoodEquivalenceCount,
    private val incrementFoodEquivalenceCount: IncrementFoodEquivalenceCount,
    private val resetFoodEquivalenceCount: ResetFoodEquivalenceCount
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
                id = savedStateHandle.toRoute<FoodResult>().foodId
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
            discardedFood.category.id
        ).fold(
            success = { replacementFoods ->
                state = FoodResultScreenState.Success(
                    discardedFood = discardedFood.toFoodUi(),
                    discardedFoodAmount = discardedFoodAmount,
                    equivalentFoods = getEquivalentFoods(
                        discardedFood = discardedFood,
                        discardedFoodAmount = discardedFoodAmount
                            .replace(oldValue = ",", newValue = ".")
                            .toDouble(),
                        replacementFoods = replacementFoods
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