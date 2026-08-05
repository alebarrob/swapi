package barrera.alejandro.swapi.presentation.category

import barrera.alejandro.swapi.presentation.model.CategoryUi

sealed class CategoryScreenState {
    data object Loading : CategoryScreenState()
    data class Success(val categories: List<CategoryUi>) : CategoryScreenState()
    data object Failure : CategoryScreenState()
}