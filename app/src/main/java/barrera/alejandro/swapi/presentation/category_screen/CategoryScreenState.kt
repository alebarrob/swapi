package barrera.alejandro.swapi.presentation.category_screen

import barrera.alejandro.swapi.presentation.model.CategoryUi

data class CategoryScreenState(
    val categories: List<CategoryUi> = emptyList(),
    val isLoading: Boolean = false
)