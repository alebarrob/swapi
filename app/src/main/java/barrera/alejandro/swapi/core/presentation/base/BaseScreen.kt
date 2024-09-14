package barrera.alejandro.swapi.core.presentation.base

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import barrera.alejandro.swapi.core.presentation.components.VerticalGradientBackground
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme

private const val HALF_DIVISOR = 2

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    SwapiTheme {
        val configuration = LocalConfiguration.current
        val screenDensity = LocalDensity.current
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current

        VerticalGradientBackground(
            colors = listOf(colorVariants.lightGreen, colors.primary),
            startY = with(screenDensity) {
                configuration.screenHeightDp.dp.toPx() / HALF_DIVISOR
            }
        ) {
            Box(modifier = modifier) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun BaseScreenPreview() {
    BaseScreen { }
}