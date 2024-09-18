package barrera.alejandro.swapi.core.presentation.base

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import barrera.alejandro.swapi.core.presentation.components.ErrorPopup
import barrera.alejandro.swapi.core.presentation.components.VerticalGradientBackground
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

private const val HALF_DIVISOR = 2

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    uiEvent: Flow<UiEvent>,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenDensity = LocalDensity.current
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current

    var showErrorPopup by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowErrorPopup -> showErrorPopup = true

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
        Box(modifier = modifier) {
            if (showErrorPopup) {
                ErrorPopup(
                    onDismiss = {
                        showErrorPopup = false
                    }
                )
            }
            content()
        }
    }
}

@Preview
@Composable
private fun BaseScreenPreview() {
    BaseScreen(uiEvent = flowOf()) { }
}