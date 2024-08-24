package barrera.alejandro.swapi.food_swap.presentation.food_selection_screen

import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi

data class FoodSelectionScreenState(
    val food: List<FoodUi> = emptyList(),
    // TODO Think about whether the category should be part of the state
    //  or come as a navigation argument when implemented
    val foodCategoryId: Int = -1,
    val isLoading: Boolean = false
)