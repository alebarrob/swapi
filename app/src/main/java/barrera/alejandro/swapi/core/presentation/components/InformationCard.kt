package barrera.alejandro.swapi.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.core.presentation.util.constant.PREVIEW_BACKGROUND
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.core.presentation.util.enums.ImagePosition
import barrera.alejandro.swapi.core.presentation.util.extension.toBoldColoredAnnotatedString

@Composable
fun InformationCard(
    text: String,
    modifier: Modifier = Modifier,
    imageResourceId: Int? = null,
    imagePosition: ImagePosition = ImagePosition.END
) {
    val typography = MaterialTheme.typography
    val dimensions = LocalDimensions.current
    val colorVariants = LocalColorVariants.current

    Box(
        modifier = if (imageResourceId == null) modifier else modifier.padding(
            top = if (imagePosition == ImagePosition.END) -dimensions.informationCardTopEndImageYOffSet
            else -dimensions.informationCardTopStartImageYOffSet
        )
    ) {
        Card(
            shape = RoundedCornerShape(size = dimensions.informationCardShapeSize),
            colors = CardDefaults.cardColors(containerColor = colorVariants.white)
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(
                        horizontal = dimensions.medium,
                        vertical = dimensions.large
                    ),
                textAlign = TextAlign.Center,
                style = typography.bodyMedium
            )
        }
        imageResourceId?.let { id ->
            Image(
                painter = painterResource(id = id),
                contentDescription = stringResource(id = R.string.happy_watermelon_icon_description),
                modifier = if (imagePosition == ImagePosition.END) {
                    Modifier
                        .align(Alignment.TopEnd)
                        .offset(
                            x = dimensions.informationCardTopEndImageXOffSet,
                            y = dimensions.informationCardTopEndImageYOffSet
                        )
                } else {
                    Modifier
                        .align(Alignment.TopStart)
                        .offset(
                            x = dimensions.informationCardTopStartImageXOffSet,
                            y = dimensions.informationCardTopStartImageYOffSet
                        )

                }
            )
        }
    }
}

@Composable
fun InformationCard(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    imageResourceId: Int? = null,
    imagePosition: ImagePosition = ImagePosition.END
) {
    val typography = MaterialTheme.typography
    val dimensions = LocalDimensions.current
    val colorVariants = LocalColorVariants.current

    Box(
        modifier = if (imageResourceId == null) modifier else modifier.padding(
            top = if (imagePosition == ImagePosition.END) -dimensions.informationCardTopEndImageYOffSet
            else -dimensions.informationCardTopStartImageYOffSet
        )
    ) {
        Card(
            shape = RoundedCornerShape(size = dimensions.informationCardShapeSize),
            colors = CardDefaults.cardColors(containerColor = colorVariants.white)
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(
                        horizontal = dimensions.medium,
                        vertical = dimensions.large
                    ),
                textAlign = TextAlign.Center,
                style = typography.bodyMedium
            )
        }
        imageResourceId?.let { id ->
            Image(
                painter = painterResource(id = id),
                contentDescription = stringResource(id = R.string.happy_watermelon_icon_description),
                modifier = if (imagePosition == ImagePosition.END) {
                    Modifier
                        .align(Alignment.TopEnd)
                        .offset(
                            x = dimensions.informationCardTopEndImageXOffSet,
                            y = dimensions.informationCardTopEndImageYOffSet
                        )
                } else {
                    Modifier
                        .align(Alignment.TopStart)
                        .offset(
                            x = dimensions.informationCardTopStartImageXOffSet,
                            y = dimensions.informationCardTopStartImageYOffSet
                        )

                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
fun PreviewInformationCard() {
    SwapiTheme {
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current
        val dimensions = LocalDimensions.current
        val boldColoredWords = mapOf(
            stringResource(id = R.string.bold_colored_swapi) to colors.secondary,
            stringResource(id = R.string.bold_colored_category) to colorVariants.darkGreen
        )

        InformationCard(
            text = stringResource(id = R.string.categories_screen_message).toBoldColoredAnnotatedString(
                boldColoredWords
            ),
            modifier = Modifier.padding(all = dimensions.large),
            imageResourceId = R.drawable.happy_watermelon_ic,
            imagePosition = ImagePosition.END
        )
    }
}