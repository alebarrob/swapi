package barrera.alejandro.swapi.core.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Black = Color(color = 0xFF000000)
val White = Color(color = 0xFFFFFFFF)
val Grey = Color(color = 0xFFD9D9D9)
val Green = Color(color = 0xFF60D394)
val Red = Color(color = 0xFFCD3858)
val Gold = Color(color = 0xFFFDDE4F)

data class ColorVariants(
    val darkGreen: Color = Color(color = 0xFF2A723A),
    val lightGreen: Color = Color(color = 0xFFBAFF97),
    val darkGold: Color = Color(color = 0xFFE5BD02)
)

val LocalColorVariants = staticCompositionLocalOf { ColorVariants() }