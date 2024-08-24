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
    val informationCardTopStartImageXOffSet: Dp = 3.dp,
    val informationCardTopStartImageYOffSet: Dp = -(93).dp,
    val informationCardTopEndImageXOffSet: Dp = 4.dp,
    val informationCardTopEndImageYOffSet: Dp = -(85).dp,

    val imageCardShapeSize: Dp = 20.dp,
    val imageCardSize: Dp = 160.dp,
    val imageCardVerticalArrangementSpacedBy: Dp = 12.dp,

    val actionButtonShapeSize: Dp = 12.dp,
    val actionButtonBorderWidth: Dp = 2.dp,
    val actionButtonHorizontalContentPadding: Dp = 52.dp,
    val actionButtonVerticalContentPadding: Dp = 16.dp,

    val screenPaddingTop: Dp = 100.dp
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }