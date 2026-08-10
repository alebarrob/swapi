package barrera.alejandro.swapi.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import barrera.alejandro.swapi.presentation.components.preview.FoodAmountCardPreviewParameterProvider
import barrera.alejandro.swapi.presentation.components.preview.FoodAmountCardPreviewState
import barrera.alejandro.swapi.presentation.model.FoodUi
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.util.constant.PREVIEW_BACKGROUND

@Composable
fun FoodAmountCard(
    food: FoodUi,
    amount: String,
    onAmountChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorText: String? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        ImageCard(
            text = food.name,
            imageResourceId = food.imageResourceId,
        )

        AmountTextField(
            unit = food.unitUi,
            amount = amount,
            onAmountChange = onAmountChange,
            isError = isError,
            errorText = errorText,
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND,
)
@Composable
private fun FoodAmountCardPreview(
    @PreviewParameter(FoodAmountCardPreviewParameterProvider::class)
    previewState: FoodAmountCardPreviewState,
) {
    var amount by remember(previewState.amount) {
        mutableStateOf(previewState.amount)
    }

    SwapiTheme {
        FoodAmountCard(
            food = previewState.food,
            amount = amount,
            onAmountChange = { amount = it },
            isError = previewState.isError,
            errorText = previewState.errorText,
        )
    }
}