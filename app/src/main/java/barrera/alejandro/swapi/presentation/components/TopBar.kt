package barrera.alejandro.swapi.presentation.components

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
import barrera.alejandro.swapi.presentation.enums.Screen
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.SwapiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    route: String,
    onBackClick: () -> Unit,
    onResetClick: () -> Unit,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorVariants = LocalColorVariants.current
    val typography = MaterialTheme.typography

    TopAppBar(
        modifier = modifier,
        title = {
            if (!route.contains(Screen.Category.name)) {
                Text(
                    text = stringResource(id = R.string.back),
                    color = colorVariants.white,
                    style = typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        navigationIcon = {
            if (!route.contains(Screen.Category.name)) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_icon_description)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorVariants.darkGreen,
            navigationIconContentColor = colorVariants.white
        ),
        actions = {
            if (!route.contains(Screen.Category.name)) {
                IconButton(onClick = onResetClick) {
                    Image(
                        painter = painterResource(id = R.drawable.reset_ic),
                        contentDescription = stringResource(id = R.string.reset_icon_description)
                    )
                }
            }
            IconButton(onClick = onInfoClick) {
                Image(
                    painter = painterResource(id = R.drawable.info_ic),
                    contentDescription = stringResource(id = R.string.info_icon_description)
                )
            }
        }
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    SwapiTheme {
        TopBar(
            route = "barrera.alejandro.swapi.presentation.navigation.FoodSelection",
            onBackClick = {},
            onResetClick = {},
            onInfoClick = {}
        )
    }
}