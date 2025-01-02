package barrera.alejandro.swapi.presentation.base

import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import barrera.alejandro.swapi.presentation.components.VerticalGradientBackground
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

private const val HALF_DIVISOR = 2

@Composable
fun BaseScreen(
    uiEvent: Flow<UiEvent>,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenDensity = LocalDensity.current
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current

    LaunchedEffect(key1 = Unit) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> Toast.makeText(
                    context,
                    event.message.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    VerticalGradientBackground(
        colors = listOf(colorVariants.lightGreen, colors.primary),
        startY = with(screenDensity) {
            configuration.screenHeightDp.dp.toPx() / HALF_DIVISOR
        }
    ) {
        content()
    }
}

@Preview
@Composable
private fun BaseScreenPreview() {
    SwapiTheme {
        BaseScreen(uiEvent = flowOf()) { }
    }
}