package barrera.alejandro.swapi.presentation.food_selection

import barrera.alejandro.swapi.presentation.model.FoodUi

object FoodSelectionContract {
    sealed interface State {
        data object Loading : State

        data class Success(val foods: List<FoodUi>) : State

        data object Failure : State
    }
}