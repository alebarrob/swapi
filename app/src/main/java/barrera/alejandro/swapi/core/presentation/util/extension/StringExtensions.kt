package barrera.alejandro.swapi.core.presentation.util.extension

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import java.util.Locale

fun String.toBoldColoredAnnotatedString(
    chunksToBoldAndColor: Map<String, Color>
) = buildAnnotatedString {
    var currentIndex = 0
    val lowerCaseBaseString = this@toBoldColoredAnnotatedString.lowercase(Locale.getDefault())
    val lowerCaseChunksToBoldAndColor = chunksToBoldAndColor.mapKeys { (key, _)
        -> key.lowercase(Locale.getDefault())
    }

    while (currentIndex < lowerCaseBaseString.length) {
        var matched = false

        for ((chunk, color) in lowerCaseChunksToBoldAndColor) {
            if (lowerCaseBaseString.startsWith(prefix = chunk, startIndex = currentIndex)) {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = color)) {
                    append(
                        this@toBoldColoredAnnotatedString.substring(
                            range = currentIndex until currentIndex.plus(chunk.length)
                        )
                    )
                }
                currentIndex += chunk.length
                matched = true
                break
            }
        }

        if (!matched) {
            append(this@toBoldColoredAnnotatedString[currentIndex])
            currentIndex++
        }
    }
}