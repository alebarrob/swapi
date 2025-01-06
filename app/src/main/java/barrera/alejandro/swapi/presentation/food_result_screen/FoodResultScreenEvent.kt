package barrera.alejandro.swapi.presentation.food_result_screen

sealed class FoodResultScreenEvent {
    data object LoadEquivalentFood : FoodResultScreenEvent()
    data object ShowAdIfNeeded: FoodResultScreenEvent()
}