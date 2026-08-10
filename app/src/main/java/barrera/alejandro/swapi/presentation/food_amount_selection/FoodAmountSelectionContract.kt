package barrera.alejandro.swapi.presentation.food_amount_selection

import barrera.alejandro.swapi.presentation.model.FoodUi

object FoodAmountSelectionContract {

    data class FormState(
        val amount: String = "",
        val amountHasError: Boolean = false,
    )

    sealed interface State {
        data object Loading : State

        data class Success(
            val food: FoodUi,
            val form: FormState,
        ) : State

        data object Failure : State
    }

    sealed interface Action {
        data class AmountChanged(val amount: String) : Action

        data object CalculateClicked : Action
    }

    sealed interface Effect {
        data class NavigateToEquivalences(
            val foodId: Int,
            val amount: String,
        ) : Effect
    }
}