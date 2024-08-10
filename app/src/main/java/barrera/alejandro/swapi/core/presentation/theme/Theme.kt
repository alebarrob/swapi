package barrera.alejandro.swapi.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val LightColorScheme = lightColorScheme(
    primary = Green,
    secondary = Red,
    tertiary = Gold,
    surface = White,
    onSurface = Black,
    errorContainer = Black,
    onErrorContainer = White,
    outline = Grey,
    scrim = Grey
)

@Composable
fun SwapiTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        values = arrayOf(
            LocalDimensions provides Dimensions(),
            LocalColorVariants provides ColorVariants()
        )
    ) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            typography = Typography,
            content = content
        )
    }
}