package barrera.alejandro.swapi.food_swap.presentation.food_selection_screen

sealed class FoodSelectionScreenEvent {
    data object LoadFood : FoodSelectionScreenEvent()
}