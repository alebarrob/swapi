package barrera.alejandro.swapi.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme

private const val SCREEN_TOP = 0.0f

/**
 * Draw a vertical gradient background from top to bottom.
 *
 * @param colors The colors used in the gradient. They are positioned equally
 *               from the startY to endY.
 * @param modifier The modifier to be applied to the Box composable.
 * @param startY The starting position of the gradient along the Y axis, default is top of the screen.
 * @param endY The ending position of the gradient along the Y axis, default is bottom of the screen.
 * @param content The composable to be drawn on top of the gradient background.
 */
@Composable
fun VerticalGradientBackground(
    colors: List<Color>,
    modifier: Modifier = Modifier,
    startY: Float = SCREEN_TOP,
    endY: Float = Float.POSITIVE_INFINITY,
    content: @Composable () -> Unit
) {
    val gradient = remember {
        Brush.verticalGradient(
            colors = colors,
            startY = startY,
            endY = endY
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(brush = gradient)
    ) {
        content()
    }
}

@Preview
@Composable
fun PreviewGradientBackground() {
    SwapiTheme {
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current

        VerticalGradientBackground(colors = listOf(colorVariants.lightGreen, colors.primary)) { }
    }
}