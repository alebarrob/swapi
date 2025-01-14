package barrera.alejandro.swapi.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.LocalDimensions

@Composable
fun MarketingImage(
    imageId: Int,
    modifier: Modifier = Modifier
) {
    val colorVariants = LocalColorVariants.current
    val dimensions = LocalDimensions.current

    Box(
        modifier = modifier
            .border(
                width = dimensions.marketingImageBorderWidth,
                color = colorVariants.darkGold,
                shape = RoundedCornerShape(dimensions.medium)
            )
            .clip(RoundedCornerShape(dimensions.medium))
            .size(dimensions.marketingImageSize)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = stringResource(R.string.default_icon_description),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
private fun PreviewMarketingImage() {
    MarketingImage(imageId = R.drawable.monica_picture)
}