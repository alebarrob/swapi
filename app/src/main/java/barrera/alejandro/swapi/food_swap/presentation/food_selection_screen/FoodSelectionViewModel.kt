package barrera.alejandro.swapi.food_swap.presentation.food_selection_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import barrera.alejandro.swapi.core.presentation.base.BaseViewModel
import barrera.alejandro.swapi.core.presentation.base.UiEvent
import barrera.alejandro.swapi.core.presentation.navigation.FoodSelection
import barrera.alejandro.swapi.core.util.annotation.GetFoodsByCategoryIdUseCase
import barrera.alejandro.swapi.food_swap.domain.model.Food
import barrera.alejandro.swapi.food_swap.domain.use_case.GetFoodsByCategoryId
import barrera.alejandro.swapi.food_swap.domain.use_case.SuspendUseCase
import barrera.alejandro.swapi.food_swap.presentation.mapper.toFoodUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodSelectionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    @GetFoodsByCategoryIdUseCase
    private val getFoodsByCategoryId: SuspendUseCase<GetFoodsByCategoryId.Params, List<Food>>
) : BaseViewModel<FoodSelectionScreenState, FoodSelectionScreenEvent>(
    initialState = FoodSelectionScreenState()
) {

    override fun onEvent(event: FoodSelectionScreenEvent) {
        when (event) {
            is FoodSelectionScreenEvent.LoadFood -> loadFood()
        }
    }

    private fun loadFood() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            getFoodsByCategoryId(
                params = GetFoodsByCategoryId.Params(
                    savedStateHandle.toRoute<FoodSelection>().categoryId
                )
            ).fold(
                success = { foods ->
                    state = state.copy(
                        foods = foods.map { food ->
                            food.toFoodUi()
                        },
                        isLoading = false
                    )
                },
                failure = {
                    state = state.copy(isLoading = false)
                    sendUiEvent(UiEvent.ShowErrorPopup)
                }
            )
        }
    }
}