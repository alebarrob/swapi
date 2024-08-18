package barrera.alejandro.swapi.food_swap.presentation.category_screen

sealed class CategoryScreenEvent {
    data object LoadCategories : CategoryScreenEvent()
}