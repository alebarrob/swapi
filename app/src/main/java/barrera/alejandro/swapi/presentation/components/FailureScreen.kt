package barrera.alejandro.swapi.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.util.constant.PREVIEW_BACKGROUND

@Composable
fun FailureScreen(modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    val colorVariants = LocalColorVariants.current

    var showErrorPopup by rememberSaveable { mutableStateOf(true) }

    Box(modifier = modifier.fillMaxSize()) {
        if (showErrorPopup) AlertPopup(
            onDismiss = {
                showErrorPopup = false
            },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.error_watermelon_ic),
                    contentDescription = stringResource(R.string.default_icon_description)
                )
            },
            body = {
                Text(
                    text = stringResource(id = R.string.error_popup_text),
                    style = typography.labelMedium,
                    color = colorVariants.white,
                    textAlign = TextAlign.Center
                )
            },
            contentColor = colorVariants.white,
            containerColor = colorVariants.black,
            buttonResourceId = R.string.error_popup_ok,
            titleResourceId = R.string.error_popup_title
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND,
)
@Composable
private fun PreviewFailureScreen() {
    SwapiTheme {
        FailureScreen()
    }
}