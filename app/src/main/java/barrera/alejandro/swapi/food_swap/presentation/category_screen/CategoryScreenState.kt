package barrera.alejandro.swapi.food_swap.presentation.category_screen

import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi

data class CategoryScreenState(
    val categories: List<CategoryUi> = emptyList(),
    val isLoading: Boolean = false
)