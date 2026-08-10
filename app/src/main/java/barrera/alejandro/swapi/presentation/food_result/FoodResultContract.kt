package barrera.alejandro.swapi.presentation.food_result

import barrera.alejandro.swapi.presentation.model.FoodUi

object FoodResultContract {

    sealed interface State {

        data object Loading : State

        data class Success(
            val discardedFood: FoodUi,
            val discardedFoodAmount: String,
            val equivalentFoods: List<FoodUi>,
            val adState: AdState = AdState.Checking,
        ) : State

        data object Failure : State
    }

    sealed interface AdState {
        data object Checking : AdState
        data object Required : AdState
        data object Completed : AdState
    }

    sealed interface Action {
        data object AdFinished : Action
    }
}