package barrera.alejandro.swapi.presentation.category

sealed class CategoryScreenEvent {
    data object LoadCategories : CategoryScreenEvent()
}