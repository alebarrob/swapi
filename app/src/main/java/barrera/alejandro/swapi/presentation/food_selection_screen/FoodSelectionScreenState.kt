package barrera.alejandro.swapi.presentation.food_selection_screen

import barrera.alejandro.swapi.presentation.model.FoodUi

data class FoodSelectionScreenState(
    val foods: List<FoodUi> = emptyList(),
    val isLoading: Boolean = false
)