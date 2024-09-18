package barrera.alejandro.swapi.food_swap.presentation.food_selection_screen

import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi

data class FoodSelectionScreenState(
    val foods: List<FoodUi> = emptyList(),
    val isLoading: Boolean = false
)