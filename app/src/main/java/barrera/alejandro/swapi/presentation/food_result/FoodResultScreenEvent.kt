package barrera.alejandro.swapi.presentation.food_result

sealed class FoodResultScreenEvent {
    data object LoadEquivalentFood : FoodResultScreenEvent()
    data object ShowAdIfNeeded: FoodResultScreenEvent()
}