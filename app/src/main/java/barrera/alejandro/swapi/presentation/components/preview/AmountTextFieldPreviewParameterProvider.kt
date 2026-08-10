package barrera.alejandro.swapi.presentation.components.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import barrera.alejandro.swapi.presentation.model.UnitUi

internal data class AmountTextFieldPreviewState(
    val unit: UnitUi,
    val amount: String,
    val isError: Boolean = false,
    val errorText: String? = null,
)

internal class AmountTextFieldPreviewParameterProvider :
    PreviewParameterProvider<AmountTextFieldPreviewState> {

    private val states = listOf(
        AmountTextFieldPreviewState(
            unit = ComponentPreviewData.gramsUnit,
            amount = "",
        ),
        AmountTextFieldPreviewState(
            unit = ComponentPreviewData.gramsUnit,
            amount = "120",
        ),
        AmountTextFieldPreviewState(
            unit = ComponentPreviewData.gramsUnit,
            amount = "25,",
            isError = true,
            errorText = "Introduce una cantidad válida",
        ),
    )

    override val values: Sequence<AmountTextFieldPreviewState> =
        states.asSequence()

    override fun getDisplayName(index: Int): String? =
        when (index) {
            0 -> "Empty"
            1 -> "Filled"
            2 -> "Error"
            else -> null
        }
}