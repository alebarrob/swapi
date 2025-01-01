package barrera.alejandro.swapi.presentation.food_amount_selection_screen

import barrera.alejandro.swapi.presentation.model.FoodUi

data class FoodAmountSelectionScreenState(
    val food: FoodUi? = null,
    val isLoading: Boolean = false
)