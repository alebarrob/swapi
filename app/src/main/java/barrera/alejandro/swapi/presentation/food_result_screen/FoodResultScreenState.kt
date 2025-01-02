package barrera.alejandro.swapi.presentation.food_result_screen

import barrera.alejandro.swapi.presentation.model.FoodUi

sealed class FoodResultScreenState {
    data object Loading : FoodResultScreenState()
    data class Success(
        val discardedFood: FoodUi,
        val discardedFoodAmount: String,
        val equivalentFoods: List<FoodUi>
    ) : FoodResultScreenState()
    data object Failure : FoodResultScreenState()
}