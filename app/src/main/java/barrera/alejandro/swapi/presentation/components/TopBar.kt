package barrera.alejandro.swapi.presentation.components

import androidx.compose.foundation.Image
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
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.SwapiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    canNavigateBack: Boolean,
    onBackClick: () -> Unit,
    onDietitianClick: () -> Unit,
    onInfoClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colorVariants = LocalColorVariants.current
    val typography = MaterialTheme.typography

    TopAppBar(
        modifier = modifier,
        title = {
            if (canNavigateBack) {
                Text(
                    text = stringResource(id = R.string.back),
                    color = colorVariants.white,
                    style = typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = onBackClick,
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.arrow_back_ic,
                        ),
                        contentDescription = stringResource(
                            id = R.string.back_icon_description,
                        ),
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorVariants.darkGreen,
            navigationIconContentColor = colorVariants.white,
        ),
        actions = {
            IconButton(
                onClick = onDietitianClick,
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.dietitian_ic,
                    ),
                    contentDescription = stringResource(
                        id = R.string.dietitian_icon_description,
                    ),
                )
            }

            IconButton(
                onClick = onInfoClick,
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.info_ic,
                    ),
                    contentDescription = stringResource(
                        id = R.string.info_icon_description,
                    ),
                )
            }

            if (canNavigateBack) {
                IconButton(
                    onClick = onResetClick,
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.reset_ic,
                        ),
                        contentDescription = stringResource(
                            id = R.string.reset_icon_description,
                        ),
                    )
                }
            }
        },
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    SwapiTheme {
        TopBar(
            canNavigateBack = true,
            onBackClick = {},
            onResetClick = {},
            onInfoClick = {},
            onDietitianClick = {},
        )
    }
}