package barrera.alejandro.swapi.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.SwapiTheme

@Composable
fun AlertPopup(
    onDismiss: () -> Unit,
    icon: @Composable () -> Unit,
    buttonResourceId: Int,
    titleResourceId: Int,
    body: @Composable () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    val typography = MaterialTheme.typography

    AlertDialog(
        modifier = modifier,
        icon = {
            icon()
        },
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = titleResourceId),
                style = typography.labelLarge,
                color = contentColor,
                textAlign = TextAlign.Center
            )
        },
        text = {
            body()
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = contentColor)
            ) {
                Text(
                    text = stringResource(id = buttonResourceId),
                    color = containerColor,
                    style = typography.labelMedium
                )
            }
        },
        containerColor = containerColor
    )
}

@Preview
@Composable
private fun ErrorPopupPreview(){
    SwapiTheme {
        val typography = MaterialTheme.typography
        val colorVariants = LocalColorVariants.current

        AlertPopup(
            onDismiss = {},
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
            buttonResourceId = R.string.error_popup_ok,
            contentColor = colorVariants.white,
            containerColor = colorVariants.black,
            titleResourceId = R.string.error_popup_title
        )
    }
}