package barrera.alejandro.swapi.food_swap.presentation.food_result_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.base.BaseViewModel
import barrera.alejandro.swapi.core.presentation.base.UiEvent
import barrera.alejandro.swapi.core.presentation.navigation.FoodResult
import barrera.alejandro.swapi.core.presentation.util.UiText
import barrera.alejandro.swapi.core.util.annotation.GetEquivalentFoodUseCase
import barrera.alejandro.swapi.core.util.annotation.GetFoodByCategoryIdUseCase
import barrera.alejandro.swapi.core.util.annotation.GetFoodByIdUseCase
import barrera.alejandro.swapi.food_swap.domain.model.Food
import barrera.alejandro.swapi.food_swap.domain.use_case.GetEquivalentFood
import barrera.alejandro.swapi.food_swap.domain.use_case.GetFoodByCategoryId
import barrera.alejandro.swapi.food_swap.domain.use_case.GetFoodById
import barrera.alejandro.swapi.food_swap.domain.use_case.SuspendUseCase
import barrera.alejandro.swapi.food_swap.domain.use_case.UseCase
import barrera.alejandro.swapi.food_swap.presentation.mapper.toFoodUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodResultViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    @GetFoodByIdUseCase
    private val getFoodById:
        SuspendUseCase<@JvmSuppressWildcards GetFoodById.Params, @JvmSuppressWildcards Food>,
    @GetFoodByCategoryIdUseCase
    private val getFoodByCategoryId:
        SuspendUseCase<@JvmSuppressWildcards GetFoodByCategoryId.Params, @JvmSuppressWildcards List<Food>>,
    @GetEquivalentFoodUseCase
    private val getEquivalentFood:
        UseCase<@JvmSuppressWildcards GetEquivalentFood.Params, @JvmSuppressWildcards List<Food>>
) : BaseViewModel<FoodResultScreenState, FoodResultScreenEvent>(
    initialState = FoodResultScreenState(
        discardedFoodAmount = savedStateHandle.toRoute<FoodResult>().amount
    )
) {

    override fun onEvent(event: FoodResultScreenEvent) {
        when (event) {
            is FoodResultScreenEvent.LoadEquivalentFood -> loadEquivalentFood()
        }
    }

    private fun loadEquivalentFood() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            getFoodById(
                params = GetFoodById.Params(id = savedStateHandle.toRoute<FoodResult>().foodId)
            ).fold(
                success = { discardedFood ->
                    state = state.copy(discardedFood = discardedFood.toFoodUi())
                    getFoodByCategoryId(
                        params = GetFoodByCategoryId.Params(discardedFood.category.id)
                    ).fold(
                        success = { replacementFood ->
                            state = state.copy(
                                isLoading = false,
                                equivalentFood = getEquivalentFood(
                                    GetEquivalentFood.Params(
                                        discardedFood = discardedFood,
                                        discardedFoodAmount = state.discardedFoodAmount.toDouble(),
                                        replacementFood = replacementFood
                                    )
                                ).map { food ->
                                    food.toFoodUi()
                                }
                            )
                        },
                        failure = { onFailure() }
                    )
                },
                failure = { onFailure() }
            )
        }
    }

    private suspend fun onFailure() {
        state = state.copy(isLoading = false)
        sendUiEvent(UiEvent.ShowErrorPopup)
    }
}