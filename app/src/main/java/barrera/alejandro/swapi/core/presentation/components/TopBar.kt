package barrera.alejandro.swapi.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    isVisible: Boolean,
    onBackClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorVariants = LocalColorVariants.current
    val typography = MaterialTheme.typography

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it })
    ) {
        TopAppBar(
            modifier = modifier,
            title = {
                Text(
                    text = stringResource(id = R.string.back),
                    color = colorVariants.white,
                    style = typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_icon_description)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorVariants.darkGreen,
                navigationIconContentColor = colorVariants.white
            ),
            actions = {
                IconButton(onClick = onResetClick) {
                    Image(
                        painter = painterResource(id = R.drawable.reset),
                        contentDescription = stringResource(id = R.string.reset_icon_description)
                    )
                }
            }
        )
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    SwapiTheme {
        TopBar(
            isVisible = true,
            onBackClick = {},
            onResetClick = {}
        )
    }
}