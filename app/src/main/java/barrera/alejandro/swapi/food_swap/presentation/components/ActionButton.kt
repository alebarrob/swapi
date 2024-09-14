package barrera.alejandro.swapi.food_swap.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.core.presentation.util.constant.PREVIEW_BACKGROUND

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val colorVariants = LocalColorVariants.current
    val typography = MaterialTheme.typography

    Button(
        onClick = onClick,
        modifier = modifier.shadow(
            elevation = dimensions.small,
            shape = RoundedCornerShape(size = dimensions.actionButtonShapeSize)
        ),
        shape = RoundedCornerShape(size = dimensions.actionButtonShapeSize),
        border = BorderStroke(
            width = dimensions.actionButtonBorderWidth,
            color = colorVariants.darkGreen
        ),
        contentPadding = PaddingValues(
            horizontal = dimensions.actionButtonHorizontalContentPadding,
            vertical = dimensions.actionButtonVerticalContentPadding
        ),
        colors = ButtonDefaults.buttonColors(containerColor = colorVariants.white)
    ) {
        Text(
            text = text,
            style = typography.labelLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
private fun ActionButtonPreview() {
    SwapiTheme {
        val dimensions = LocalDimensions.current

        ActionButton(
            text = stringResource(id = R.string.categories_screen_button_text),
            onClick = { },
            modifier = Modifier.padding(horizontal = dimensions.large, vertical = dimensions.large)
        )
    }
}