package barrera.alejandro.swapi.food_swap.presentation.food_result_screen

sealed class FoodResultScreenEvent {
    data object LoadEquivalentFood : FoodResultScreenEvent()
}