package barrera.alejandro.swapi.core.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,
    val giant: Dp = 128.dp,

    val informationCardShapeSize: Dp = 10.dp,
    val informationCardTextVerticalPadding: Dp = 45.dp,
    val informationCardWithDecorativeImageTopPadding: Dp = 93.dp,
    val informationCardWithHighlightImageTopPadding: Dp = 16.dp,
    val informationCardWithHighlightImageHorizontalPadding: Dp = 12.dp,
    val informationCardTopStartDecorativeImageXOffSet: Dp = 3.dp,
    val informationCardTopStartDecorativeImageYOffSet: Dp = -(93).dp,
    val informationCardTopEndDecorativeImageXOffSet: Dp = 4.dp,
    val informationCardTopEndDecorativeImageYOffSet: Dp = -(85).dp,
    val informationCardTopStartHighlightImageXOffSet: Dp = -(12).dp,
    val informationCardTopStartHighlightImageYOffSet: Dp = -(16).dp,
    val informationCardTopEndHighlightImageXOffSet: Dp = 12.dp,
    val informationCardTopEndHighlightImageYOffSet: Dp = -(16).dp,

    val imageCardShapeSize: Dp = 20.dp,
    val imageCardSize: Dp = 160.dp,
    val imageCardVerticalArrangementSpacedBy: Dp = 12.dp,
    val imageCardHighlightImageOffSet: Dp = -(10).dp,
    val imageCardHighlightImagePadding: Dp = 10.dp,

    val actionButtonShapeSize: Dp = 12.dp,
    val actionButtonBorderWidth: Dp = 2.dp,
    val actionButtonHorizontalContentPadding: Dp = 52.dp,
    val actionButtonVerticalContentPadding: Dp = 16.dp,

    val screenPaddingTop: Dp = 100.dp,

    val highlightImageSmallShapeSize: Dp = 15.dp,
    val highlightImageMediumShapeSize: Dp = 15.dp,
    val highlightImageSmallSize: Dp = 30.dp,
    val highlightImageMediumSize: Dp = 30.dp,
    val highlightImageBorderWidth: Dp = 1.dp
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }