package barrera.alejandro.swapi.presentation.food_selection

sealed class FoodSelectionScreenEvent {
    data object LoadFood : FoodSelectionScreenEvent()
}