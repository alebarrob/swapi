package barrera.alejandro.swapi.food_swap.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.food_swap.presentation.model.UnitUi

@Composable
fun AmountTextField(
    unit: UnitUi,
    amount: String,
    onAmountChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false
) {
    val dimensions = LocalDimensions.current
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = amount,
        onValueChange = onAmountChange,
        label = {
            Text(text = unit.name)
        },
        shape = RoundedCornerShape(size = dimensions.small),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        singleLine = true,
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
            errorLabelColor = colors.secondary
        ),
        isError = isError,
        modifier = modifier
    )
}

@Preview
@Composable
private fun AmountTextFieldPreview() {
    val unit = UnitUi(
        id = 1,
        name = "gr."
    )
    var amount by remember { mutableStateOf("") }

    SwapiTheme {
        AmountTextField(
            unit = unit,
            amount = amount,
            onAmountChange = { amount = it }
        )
    }
}

@Preview
@Composable
private fun AmountTextFieldErrorPreview() {
    val unit = UnitUi(
        id = 1,
        name = "gr."
    )
    var amount by remember { mutableStateOf("") }

    SwapiTheme {
        AmountTextField(
            unit = unit,
            amount = amount,
            onAmountChange = { amount = it },
            isError = true
        )
    }
}