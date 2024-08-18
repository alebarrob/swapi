package barrera.alejandro.swapi.core.presentation.base

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import barrera.alejandro.swapi.core.presentation.components.ScrollableContent
import barrera.alejandro.swapi.core.presentation.components.VerticalGradientBackground
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.core.presentation.util.constant.HALF_DIVISOR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun BaseScreen(
    uiEvent: Flow<UiEvent>,
    content: @Composable () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowPopup -> {}
            }
        }
    }

    SwapiTheme {
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current
        val screenDensity = LocalDensity.current
        val configuration = LocalConfiguration.current

        VerticalGradientBackground(
            colors = listOf(colorVariants.lightGreen, colors.primary),
            startY = with(screenDensity) {
                configuration.screenHeightDp.dp.toPx() / HALF_DIVISOR
            }
        ) {
            ScrollableContent {
                content()
            }
        }
    }
}

@Preview
@Composable
fun BaseScreenPreview() {
    BaseScreen(uiEvent = flowOf()) { }
}