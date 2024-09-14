package barrera.alejandro.swapi.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme

@Composable
fun ErrorPopup(onDismiss: () -> Unit) {
    val colorVariants = LocalColorVariants.current
    val typography = MaterialTheme.typography

    AlertDialog(
        icon = {
            Image(painter = painterResource(id = R.drawable.error_watermelon_ic), contentDescription = "")
        },
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = R.string.error_popup_title),
                style = typography.labelLarge,
                color = colorVariants.white,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.error_popup_text),
                style = typography.labelMedium,
                color = colorVariants.white,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = colorVariants.white)
            ) {
                Text(
                    text = stringResource(id = R.string.error_popup_ok),
                    style = typography.labelLarge
                )
            }
        },
        containerColor = colorVariants.black
    )
}

@Preview
@Composable
private fun ErrorPopupPreview(){
    SwapiTheme {
        ErrorPopup(onDismiss = {})
    }
}