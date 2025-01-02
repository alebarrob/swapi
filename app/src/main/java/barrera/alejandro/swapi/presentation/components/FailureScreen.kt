package barrera.alejandro.swapi.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.presentation.base.BaseScreen
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun FailureScreen(modifier: Modifier = Modifier) {
    var showErrorPopup by rememberSaveable { mutableStateOf(true) }

    Box(modifier = modifier.fillMaxSize()) {
        if (showErrorPopup) ErrorPopup(
            onDismiss = {
                showErrorPopup = false
            }
        )
    }
}

@Preview
@Composable
private fun PreviewFailureScreen() {
    SwapiTheme {
        BaseScreen(uiEvent = flowOf()) {
            FailureScreen()
        }
    }
}