package barrera.alejandro.swapi.food_swap.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.core.presentation.util.constant.PREVIEW_BACKGROUND

private const val LONG_SENTENCE_LENGTH = 26

@Composable
fun ImageCard(
    onClick: (String) -> Unit,
    text: String,
    imageResourceId: Int,
    modifier: Modifier = Modifier
) {
    val typography = MaterialTheme.typography
    val dimensions = LocalDimensions.current
    val colorVariants = LocalColorVariants.current

    Card(
        modifier = modifier
            .size(size = dimensions.imageCardSize)
            .clickable {
                onClick(text)
            },
        shape = RoundedCornerShape(size = dimensions.imageCardShapeSize),
        colors = CardDefaults.cardColors(containerColor = colorVariants.white)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = dimensions.medium,
                    horizontal = dimensions.medium
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = dimensions.imageCardVerticalArrangementSpacedBy,
                alignment = Alignment.CenterVertically
            )
        ) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = stringResource(R.string.food_icon_description)
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = if (text.length < LONG_SENTENCE_LENGTH) typography.labelMedium
                        else typography.labelSmall
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
fun PreviewShortTextImageCard() {
    SwapiTheme {
        val dimensions = LocalDimensions.current

        ImageCard(
            onClick = {},
            text = "Pera",
            imageResourceId = R.drawable.pear_ic,
            modifier = Modifier.padding(all = dimensions.large)
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
fun PreviewMediumTextImageCard() {
    SwapiTheme {
        val dimensions = LocalDimensions.current

        ImageCard(
            onClick = {},
            text = "Piña natural",
            imageResourceId = R.drawable.pineapple_ic,
            modifier = Modifier.padding(all = dimensions.large)
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
fun PreviewLongTextImageCard() {
    SwapiTheme {
        val dimensions = LocalDimensions.current

        ImageCard(
            onClick = {},
            text = "Puré de patata deshidratado (En Polvo)",
            imageResourceId = R.drawable.potato_powder_ic,
            modifier = Modifier.padding(all = dimensions.large)
        )
    }
}