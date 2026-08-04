package barrera.alejandro.swapi.presentation.food_selection_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import barrera.alejandro.swapi.domain.use_case.GetFoodsByCategoryId
import barrera.alejandro.swapi.presentation.base.BaseViewModel
import barrera.alejandro.swapi.presentation.mapper.toFoodUi
import barrera.alejandro.swapi.presentation.navigation.FoodSelection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodSelectionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getFoodsByCategoryId: GetFoodsByCategoryId
) : BaseViewModel<FoodSelectionScreenState, FoodSelectionScreenEvent>(
    initialState = FoodSelectionScreenState.Loading
) {

    init {
        onEvent(FoodSelectionScreenEvent.LoadFood)
    }

    override fun onEvent(event: FoodSelectionScreenEvent) {
        when (event) {
            is FoodSelectionScreenEvent.LoadFood -> loadFood()
        }
    }

    private fun loadFood() {
        viewModelScope.launch {
            getFoodsByCategoryId(
                savedStateHandle.toRoute<FoodSelection>().categoryId
            ).fold(
                success = { foods ->
                    state = FoodSelectionScreenState.Success(
                        foods = foods.map { food ->
                            food.toFoodUi()
                        }
                    )
                },
                failure = {
                    state = FoodSelectionScreenState.Failure
                }
            )
        }
    }
}