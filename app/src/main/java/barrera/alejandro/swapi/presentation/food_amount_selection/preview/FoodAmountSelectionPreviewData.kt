package barrera.alejandro.swapi.presentation.food_amount_selection.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionContract.FormState
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionContract.State
import barrera.alejandro.swapi.presentation.model.CategoryUi
import barrera.alejandro.swapi.presentation.model.FoodUi
import barrera.alejandro.swapi.presentation.model.UnitUi

internal class FoodAmountSelectionStatePreviewParameterProvider :
    PreviewParameterProvider<State> {

    private val states = listOf(
        State.Loading,
        State.Success(
            food = FoodAmountSelectionPreviewData.food,
            form = FormState(),
        ),
        State.Success(
            food = FoodAmountSelectionPreviewData.food,
            form = FormState(
                amount = "25,",
                amountHasError = true,
            ),
        ),
        State.Failure,
    )

    override val values: Sequence<State> =
        states.asSequence()

    override fun getDisplayName(index: Int): String? =
        when (val state = states.getOrNull(index)) {
            State.Loading -> "Loading"
            is State.Success -> {
                if (state.form.amountHasError) {
                    "Invalid amount"
                } else {
                    "Success"
                }
            }

            State.Failure -> "Failure"
            null -> null
        }
}

private object FoodAmountSelectionPreviewData {

    val food = FoodUi(
        id = 6,
        name = "Fresas",
        imageResourceId = R.drawable.strawberry_ic,
        standardAmount = "250",
        categoryUi = CategoryUi(
            id = 1,
            name = "Frutas",
        ),
        unitUi = UnitUi(
            id = 1,
            name = "gr.",
        ),
    )
}