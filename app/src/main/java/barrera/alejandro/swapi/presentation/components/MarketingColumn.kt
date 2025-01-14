package barrera.alejandro.swapi.presentation.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.LocalDimensions

@Composable
fun MarketingColumn(
    text: String,
    instagramUrl: String,
    email: String,
    modifier: Modifier = Modifier
) {
    val typography = MaterialTheme.typography
    val colorVariants = LocalColorVariants.current
    val dimensions = LocalDimensions.current
    val context = LocalContext.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensions.large),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = typography.bodyLarge,
            color = colorVariants.black,
            textAlign = TextAlign.Center
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensions.socialsHorizontalArrangement),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = dimensions.medium)
        ) {
            MarketingSocial(
                iconId = R.drawable.instagram_ic,
                label = stringResource(R.string.instagram_icon_text),
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl))

                    context.startActivity(intent)
                }
            )
            MarketingSocial(
                iconId = R.drawable.email_ic,
                label = stringResource(R.string.email_icon_text),
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:$email")
                    }

                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
private fun MarketingSocial(
    iconId: Int,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val typography = MaterialTheme.typography

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensions.extraSmall),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = stringResource(R.string.default_icon_description),
            modifier = Modifier
                .size(dimensions.socialsSize)
                .clickable {
                    onClick()
                }
        )
        Text(
            text = label,
            style = typography.labelMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMarketingColumn() {
    MarketingColumn(
        text = stringResource(id = R.string.dietitian_popup_text),
        instagramUrl = stringResource(R.string.monica_instagram_url),
        email = stringResource(R.string.email_address)
    )
}

