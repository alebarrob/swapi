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
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.core.presentation.util.constant.PREVIEW_BACKGROUND
import barrera.alejandro.swapi.core.presentation.util.enums.ImagePosition
import barrera.alejandro.swapi.core.presentation.util.extension.toBoldColoredAnnotatedString

@Composable
fun InformationCard(
    text: String,
    modifier: Modifier = Modifier,
    decorativeImageResourceId: Int? = null,
    highlightImageResourceId: Int? = null,
    imagePosition: ImagePosition = ImagePosition.HIGHLIGHT_ON_START,
) {
    val dimensions = LocalDimensions.current
    val colorVariants = LocalColorVariants.current
    val typography = MaterialTheme.typography

    Box(
        modifier = modifier.padding(
            top = when {
                decorativeImageResourceId != null ->
                    dimensions.informationCardWithDecorativeImageTopPadding

                highlightImageResourceId != null ->
                    dimensions.informationCardWithHighlightImageTopPadding

                else -> dimensions.default
            },
            start = when {
                highlightImageResourceId != null && imagePosition == ImagePosition.HIGHLIGHT_ON_START ->
                    dimensions.informationCardWithHighlightImageHorizontalPadding

                else -> dimensions.default
            },
            end = when {
                highlightImageResourceId != null && imagePosition == ImagePosition.DECORATIVE_ON_START ->
                    dimensions.informationCardWithHighlightImageHorizontalPadding

                else -> dimensions.default
            }
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
                        vertical = dimensions.informationCardTextVerticalPadding
                    ),
                textAlign = TextAlign.Center,
                style = typography.bodyMedium
            )
        }
        decorativeImageResourceId?.let { id ->
            Image(
                painter = painterResource(id = id),
                contentDescription = stringResource(id = R.string.happy_watermelon_icon_description),
                modifier = if (imagePosition == ImagePosition.HIGHLIGHT_ON_START) {
                    Modifier
                        .align(Alignment.TopEnd)
                        .offset(
                            x = dimensions.informationCardTopEndDecorativeImageXOffSet,
                            y = dimensions.informationCardTopEndDecorativeImageYOffSet
                        )
                } else {
                    Modifier
                        .offset(
                            x = dimensions.informationCardTopStartDecorativeImageXOffSet,
                            y = dimensions.informationCardTopStartDecorativeImageYOffSet
                        )
                }
            )
        }
        highlightImageResourceId?.let { id ->
            HighlightImage(
                imageResourceId = id,
                modifier = if (imagePosition == ImagePosition.HIGHLIGHT_ON_START) {
                    Modifier
                        .align(Alignment.TopStart)
                        .offset(
                            x = dimensions.informationCardTopStartHighlightImageXOffSet,
                            y = dimensions.informationCardTopStartHighlightImageYOffSet
                        )
                } else {
                    Modifier
                        .align(Alignment.TopEnd)
                        .offset(
                            x = dimensions.informationCardTopEndHighlightImageXOffSet,
                            y = dimensions.informationCardTopEndHighlightImageYOffSet
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
    decorativeImageResourceId: Int? = null,
    highlightImageResourceId: Int? = null,
    imagePosition: ImagePosition = ImagePosition.HIGHLIGHT_ON_START,
) {
    val dimensions = LocalDimensions.current
    val colorVariants = LocalColorVariants.current
    val typography = MaterialTheme.typography

    Box(
        modifier = modifier.padding(
            top = when {
                decorativeImageResourceId != null -> dimensions.informationCardWithDecorativeImageTopPadding
                highlightImageResourceId != null -> dimensions.informationCardWithHighlightImageTopPadding
                else -> dimensions.default
            },
            start = if (highlightImageResourceId != null && imagePosition == ImagePosition.HIGHLIGHT_ON_START) {
                dimensions.informationCardWithHighlightImageHorizontalPadding
            } else dimensions.default,
            end = if (highlightImageResourceId != null && imagePosition == ImagePosition.DECORATIVE_ON_START) {
                dimensions.informationCardWithHighlightImageHorizontalPadding
            } else dimensions.default
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
                        vertical = dimensions.informationCardTextVerticalPadding
                    ),
                textAlign = TextAlign.Center,
                style = typography.bodyMedium
            )
        }
        decorativeImageResourceId?.let { id ->
            Image(
                painter = painterResource(id = id),
                contentDescription = stringResource(id = R.string.happy_watermelon_icon_description),
                modifier = if (imagePosition == ImagePosition.HIGHLIGHT_ON_START) {
                    Modifier
                        .align(Alignment.TopEnd)
                        .offset(
                            x = dimensions.informationCardTopEndDecorativeImageXOffSet,
                            y = dimensions.informationCardTopEndDecorativeImageYOffSet
                        )
                } else {
                    Modifier
                        .align(Alignment.TopStart)
                        .offset(
                            x = dimensions.informationCardTopStartDecorativeImageXOffSet,
                            y = dimensions.informationCardTopStartDecorativeImageYOffSet
                        )

                }
            )
        }
        highlightImageResourceId?.let { id ->
            HighlightImage(
                imageResourceId = id,
                modifier = if (imagePosition == ImagePosition.HIGHLIGHT_ON_START) {
                    Modifier
                        .align(Alignment.TopStart)
                        .offset(
                            x = dimensions.informationCardTopStartHighlightImageXOffSet,
                            y = dimensions.informationCardTopStartHighlightImageYOffSet
                        )
                } else {
                    Modifier
                        .align(Alignment.TopEnd)
                        .offset(
                            x = dimensions.informationCardTopEndHighlightImageXOffSet,
                            y = dimensions.informationCardTopEndHighlightImageYOffSet
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
private fun InformationCardHighlightImageOnStartDecorativeImageOnEndPreview() {
    SwapiTheme {
        val dimensions = LocalDimensions.current
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current
        val boldColoredWords = mapOf(
            stringResource(id = R.string.bold_colored_swapi) to colors.secondary,
            stringResource(id = R.string.bold_colored_category) to colorVariants.darkGreen
        )

        InformationCard(
            text = stringResource(id = R.string.categories_screen_message)
                .toBoldColoredAnnotatedString(boldColoredWords),
            modifier = Modifier.padding(
                end = dimensions.large,
                bottom = dimensions.large
            ),
            decorativeImageResourceId = R.drawable.happy_watermelon_ic,
            highlightImageResourceId = R.drawable.blueberry_ic,
            imagePosition = ImagePosition.HIGHLIGHT_ON_START
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
private fun InformationCardNoHighlightImageDecorativeImageOnEndPreview() {
    SwapiTheme {
        val dimensions = LocalDimensions.current
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current
        val boldColoredWords = mapOf(
            stringResource(id = R.string.bold_colored_swapi) to colors.secondary,
            stringResource(id = R.string.bold_colored_category) to colorVariants.darkGreen
        )

        InformationCard(
            text = stringResource(id = R.string.categories_screen_message)
                .toBoldColoredAnnotatedString(boldColoredWords),
            modifier = Modifier.padding(
                start = dimensions.large,
                end = dimensions.large,
                bottom = dimensions.large
            ),
            decorativeImageResourceId = R.drawable.happy_watermelon_ic,
            imagePosition = ImagePosition.HIGHLIGHT_ON_START
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
private fun InformationCardHighlightImageOnStartNoDecorativeImagePreview() {
    SwapiTheme {
        val dimensions = LocalDimensions.current
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current
        val boldColoredWords = mapOf(
            stringResource(id = R.string.bold_colored_swapi) to colors.secondary,
            stringResource(id = R.string.bold_colored_category) to colorVariants.darkGreen
        )

        InformationCard(
            text = stringResource(id = R.string.categories_screen_message)
                .toBoldColoredAnnotatedString(boldColoredWords),
            modifier = Modifier.padding(
                end = dimensions.large,
                bottom = dimensions.large
            ),
            highlightImageResourceId = R.drawable.blueberry_ic,
            imagePosition = ImagePosition.HIGHLIGHT_ON_START
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
private fun InformationCardHighlightImageOnEndDecorativeImageOnStartPreview() {
    SwapiTheme {
        val dimensions = LocalDimensions.current
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current
        val boldColoredWords = mapOf(
            stringResource(id = R.string.bold_colored_swapi) to colors.secondary,
            stringResource(id = R.string.bold_colored_category) to colorVariants.darkGreen
        )

        InformationCard(
            text = stringResource(id = R.string.categories_screen_message)
                .toBoldColoredAnnotatedString(boldColoredWords),
            modifier = Modifier.padding(
                start = dimensions.large,
                bottom = dimensions.large
            ),
            decorativeImageResourceId = R.drawable.surprised_watermelon_ic,
            highlightImageResourceId = R.drawable.blueberry_ic,
            imagePosition = ImagePosition.DECORATIVE_ON_START
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
private fun InformationCardNoHighlightImageDecorativeImageOnStartPreview() {
    SwapiTheme {
        val dimensions = LocalDimensions.current
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current
        val boldColoredWords = mapOf(
            stringResource(id = R.string.bold_colored_swapi) to colors.secondary,
            stringResource(id = R.string.bold_colored_category) to colorVariants.darkGreen
        )

        InformationCard(
            text = stringResource(id = R.string.categories_screen_message)
                .toBoldColoredAnnotatedString(boldColoredWords),
            modifier = Modifier.padding(
                start = dimensions.large,
                end = dimensions.large,
                bottom = dimensions.large
            ),
            decorativeImageResourceId = R.drawable.surprised_watermelon_ic,
            imagePosition = ImagePosition.DECORATIVE_ON_START
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
private fun InformationCardHighlightImageOnEndNoDecorativeImagePreview() {
    SwapiTheme {
        val dimensions = LocalDimensions.current
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current
        val boldColoredWords = mapOf(
            stringResource(id = R.string.bold_colored_swapi) to colors.secondary,
            stringResource(id = R.string.bold_colored_category) to colorVariants.darkGreen
        )

        InformationCard(
            text = stringResource(id = R.string.categories_screen_message)
                .toBoldColoredAnnotatedString(boldColoredWords),
            modifier = Modifier.padding(
                start = dimensions.large,
                bottom = dimensions.large
            ),
            highlightImageResourceId = R.drawable.blueberry_ic,
            imagePosition = ImagePosition.DECORATIVE_ON_START
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
private fun InformationCardNoImagesPreview() {
    SwapiTheme {
        val dimensions = LocalDimensions.current
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current
        val boldColoredWords = mapOf(
            stringResource(id = R.string.bold_colored_swapi) to colors.secondary,
            stringResource(id = R.string.bold_colored_category) to colorVariants.darkGreen
        )

        InformationCard(
            text = stringResource(id = R.string.categories_screen_message)
                .toBoldColoredAnnotatedString(boldColoredWords),
            modifier = Modifier.padding(all = dimensions.large)
        )
    }
}