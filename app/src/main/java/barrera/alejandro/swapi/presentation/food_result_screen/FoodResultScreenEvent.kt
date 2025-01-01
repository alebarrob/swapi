package barrera.alejandro.swapi.presentation.food_result_screen

sealed class FoodResultScreenEvent {
    data object LoadEquivalentFood : FoodResultScreenEvent()
}