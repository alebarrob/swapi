package barrera.alejandro.swapi.presentation.food_amount_selection

sealed class FoodAmountSelectionScreenEvent {
    data object LoadFood : FoodAmountSelectionScreenEvent()
    data object InvalidFoodAmount : FoodAmountSelectionScreenEvent()
}