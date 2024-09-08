package barrera.alejandro.swapi.food_swap.presentation.food_result_screen

import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi

data class FoodResultScreenState(
    val selectedFood: FoodUi? = null,
    val selectedAmount: String = "",
    val equivalentFood: List<FoodUi> = emptyList(),
    val isLoading: Boolean = false
)