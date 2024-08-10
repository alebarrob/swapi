 package barrera.alejandro.swapi.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import barrera.alejandro.swapi.R

 private val fontFamilyInriaSans = FontFamily(
     listOf(
         Font(
             resId = R.font.inria_sans_regular,
             weight = FontWeight.Normal
         ),
         Font(
             resId = R.font.inria_sans_bold,
             weight = FontWeight.Bold
         )
     )
 )

 private val fontFamilyLato = FontFamily(
     listOf(
         Font(
             resId = R.font.lato_regular,
             weight = FontWeight.Normal
         ),
         Font(
             resId = R.font.lato_bold,
             weight = FontWeight.Bold
         )
     )
 )

val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = fontFamilyInriaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.sp,
        textAlign = TextAlign.Left,
        color = White
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamilyInriaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.sp,
        textAlign = TextAlign.Center,
        color = Black
    ),
    labelLarge = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Bold,
        fontSize = 27.sp,
        lineHeight = 32.sp,
        textAlign = TextAlign.Center,
        color = Black
    ),
    labelMedium = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.sp,
        textAlign = TextAlign.Center,
        color = Black
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        textAlign = TextAlign.Center,
        color = Black
    )
)