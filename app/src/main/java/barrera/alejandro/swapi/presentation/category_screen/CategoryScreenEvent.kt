package barrera.alejandro.swapi.presentation.category_screen

sealed class CategoryScreenEvent {
    data object LoadCategories : CategoryScreenEvent()
}