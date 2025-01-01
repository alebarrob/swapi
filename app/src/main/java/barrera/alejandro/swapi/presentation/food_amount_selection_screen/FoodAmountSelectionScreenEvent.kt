package barrera.alejandro.swapi.presentation.food_amount_selection_screen

sealed class FoodAmountSelectionScreenEvent {
    data object LoadFood : FoodAmountSelectionScreenEvent()
    data object InvalidFoodAmount : FoodAmountSelectionScreenEvent()
}