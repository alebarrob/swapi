package barrera.alejandro.swapi.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.core.presentation.util.constant.PREVIEW_BACKGROUND

@Composable
fun LoadableContent(
    isLoading: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = colors.secondary)
        }
    } else content()
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
private fun LoadingLayoutBackground() {
    SwapiTheme {
        LoadableContent(isLoading = true) {}
    }
}