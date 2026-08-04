package barrera.alejandro.swapi.util.extension

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

fun String.toBoldColoredAnnotatedString(chunksToStyle: Map<String, Color>): AnnotatedString {
    val source = this

    return buildAnnotatedString {
        append(source)

        chunksToStyle.forEach { (chunk, color) ->
            if (chunk.isBlank()) return@forEach

            var startIndex = source.indexOf(string = chunk, ignoreCase = true)

            while (startIndex >= 0) {
                addStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold, color = color),
                    start = startIndex,
                    end = startIndex + chunk.length,
                )

                startIndex = source.indexOf(
                    string = chunk,
                    startIndex = startIndex + chunk.length,
                    ignoreCase = true,
                )
            }
        }
    }
}