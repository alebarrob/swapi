package barrera.alejandro.swapi.presentation.components.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import barrera.alejandro.swapi.presentation.model.FoodUi

internal data class FoodGridPreviewState(
    val foods: List<FoodUi>,
    val withResult: Boolean,
)

internal class FoodGridPreviewParameterProvider :
    PreviewParameterProvider<FoodGridPreviewState> {

    private val states = listOf(
        FoodGridPreviewState(
            foods = ComponentPreviewData.foods,
            withResult = false,
        ),
        FoodGridPreviewState(
            foods = ComponentPreviewData.foodsWithResult,
            withResult = true,
        ),
    )

    override val values: Sequence<FoodGridPreviewState> = states.asSequence()

    override fun getDisplayName(index: Int): String? =
        when (index) {
            0 -> "Food selection"
            1 -> "Equivalence results"
            else -> null
        }
}