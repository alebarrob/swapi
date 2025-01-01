package barrera.alejandro.swapi.presentation.food_selection_screen

sealed class FoodSelectionScreenEvent {
    data object LoadFood : FoodSelectionScreenEvent()
}