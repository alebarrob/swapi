package barrera.alejandro.swapi.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.presentation.base.BaseScreen
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    val colors = MaterialTheme.colorScheme

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = colors.secondary)
    }
}

@Preview
@Composable
private fun PreviewLoadingScreen() {
    SwapiTheme {
        BaseScreen(uiEvent = flowOf()) {
            LoadingScreen()
        }
    }
}