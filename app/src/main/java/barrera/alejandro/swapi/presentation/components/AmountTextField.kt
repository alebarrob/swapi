package barrera.alejandro.swapi.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.components.preview.AmountTextFieldPreviewParameterProvider
import barrera.alejandro.swapi.presentation.components.preview.AmountTextFieldPreviewState
import barrera.alejandro.swapi.presentation.model.UnitUi
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.util.constant.PREVIEW_BACKGROUND

@Composable
fun AmountTextField(
    unit: UnitUi,
    amount: String,
    onAmountChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorText: String? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val dimensions = LocalDimensions.current
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current

    OutlinedTextField(
        value = amount,
        onValueChange = onAmountChange,
        label = {
            Text(text = unit.name)
        },
        shape = RoundedCornerShape(size = dimensions.small),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            },
        ),
        singleLine = true,
        isError = isError,
        supportingText = if (isError && errorText != null) {
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(dimensions.extraSmall),
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.warning_ic,
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(16.dp),
                    )

                    Text(
                        text = errorText,
                    )
                }
            }
        } else {
            null
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = colorVariants.white,
            unfocusedContainerColor = colorVariants.white,
            errorContainerColor = colorVariants.white,
            focusedTextColor = colorVariants.black,
            unfocusedTextColor = colorVariants.black,
            errorTextColor = colorVariants.black,
            cursorColor = colorVariants.black,
            errorCursorColor = colors.secondary,
            focusedBorderColor = colors.primary,
            unfocusedBorderColor = colors.primary,
            errorBorderColor = colors.secondary,
            unfocusedLabelColor = colorVariants.darkGreen,
            focusedLabelColor = colorVariants.darkGreen,
            errorLabelColor = colors.secondary,
            errorSupportingTextColor = colors.secondary,
        ),
        modifier = modifier,
    )
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND,
)
@Composable
private fun AmountTextFieldPreview(
    @PreviewParameter(AmountTextFieldPreviewParameterProvider::class)
    previewState: AmountTextFieldPreviewState,
) {
    var amount by remember(previewState.amount) {
        mutableStateOf(previewState.amount)
    }

    SwapiTheme {
        AmountTextField(
            unit = previewState.unit,
            amount = amount,
            onAmountChange = { amount = it },
            isError = previewState.isError,
            errorText = previewState.errorText,
        )
    }
}