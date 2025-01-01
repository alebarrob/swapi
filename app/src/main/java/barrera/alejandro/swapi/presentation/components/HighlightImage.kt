package barrera.alejandro.swapi.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.util.constant.PREVIEW_BACKGROUND

@Composable
fun HighlightImage(
    imageResourceId: Int,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(size = dimensions.medium),
        colors = CardDefaults.cardColors(containerColor = colors.tertiary),
        border = BorderStroke(
            width = dimensions.highlightImageBorderWidth,
            color = colorVariants.darkGold
        )
    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = stringResource(id = R.string.selected_food_icon_description),
            modifier = Modifier
                .padding(all = dimensions.small)
                .size(dimensions.large)
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
private fun HighlightImagePreview() {
    SwapiTheme {
        val dimensions = LocalDimensions.current

        HighlightImage(
            imageResourceId = R.drawable.blueberry_ic,
            modifier = Modifier.padding(all = dimensions.large)
        )
    }
}