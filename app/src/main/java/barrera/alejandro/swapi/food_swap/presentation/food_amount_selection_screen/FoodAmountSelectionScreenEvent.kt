package barrera.alejandro.swapi.food_swap.presentation.food_amount_selection_screen

sealed class FoodAmountSelectionScreenEvent {
    data object LoadFood : FoodAmountSelectionScreenEvent()
    data object InvalidFoodAmount : FoodAmountSelectionScreenEvent()
}