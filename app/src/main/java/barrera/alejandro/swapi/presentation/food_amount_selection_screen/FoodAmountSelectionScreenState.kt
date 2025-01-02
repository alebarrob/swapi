package barrera.alejandro.swapi.presentation.food_amount_selection_screen

import barrera.alejandro.swapi.presentation.model.FoodUi

sealed class FoodAmountSelectionScreenState {
    data object Loading : FoodAmountSelectionScreenState()
    data class Success(val food: FoodUi) : FoodAmountSelectionScreenState()
    data object Failure : FoodAmountSelectionScreenState()
}