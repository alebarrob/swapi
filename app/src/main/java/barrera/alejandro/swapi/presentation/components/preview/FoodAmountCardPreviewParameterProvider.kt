package barrera.alejandro.swapi.presentation.components.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import barrera.alejandro.swapi.presentation.model.FoodUi

internal data class FoodAmountCardPreviewState(
    val food: FoodUi,
    val amount: String,
    val isError: Boolean = false,
    val errorText: String? = null,
)

internal class FoodAmountCardPreviewParameterProvider :
    PreviewParameterProvider<FoodAmountCardPreviewState> {

    private val states = listOf(
        FoodAmountCardPreviewState(
            food = ComponentPreviewData.blueberryFood,
            amount = "",
        ),
        FoodAmountCardPreviewState(
            food = ComponentPreviewData.blueberryFood,
            amount = "120",
        ),
        FoodAmountCardPreviewState(
            food = ComponentPreviewData.blueberryFood,
            amount = "25,",
            isError = true,
            errorText = "Introduce una cantidad válida",
        ),
    )

    override val values: Sequence<FoodAmountCardPreviewState> =
        states.asSequence()

    override fun getDisplayName(index: Int): String? =
        when (index) {
            0 -> "Empty"
            1 -> "Filled"
            2 -> "Error"
            else -> null
        }
}