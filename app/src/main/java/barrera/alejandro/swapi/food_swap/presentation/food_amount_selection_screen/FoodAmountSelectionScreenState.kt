package barrera.alejandro.swapi.food_swap.presentation.food_amount_selection_screen

import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi

data class FoodAmountSelectionScreenState(
    val food: FoodUi,
    val amount: String
)