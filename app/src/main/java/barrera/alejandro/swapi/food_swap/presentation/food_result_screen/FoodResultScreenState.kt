package barrera.alejandro.swapi.food_swap.presentation.food_result_screen

import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi

data class FoodResultScreenState(
    val discardedFood: FoodUi? = null,
    val discardedFoodAmount: String,
    val equivalentFoods: List<FoodUi> = emptyList(),
    val isLoading: Boolean = false
)