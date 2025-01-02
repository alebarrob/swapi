package barrera.alejandro.swapi.presentation.food_selection_screen

import barrera.alejandro.swapi.presentation.model.FoodUi

sealed class FoodSelectionScreenState {
    data object Loading : FoodSelectionScreenState()
    data class Success(val foods: List<FoodUi>) : FoodSelectionScreenState()
    data object Failure : FoodSelectionScreenState()
}