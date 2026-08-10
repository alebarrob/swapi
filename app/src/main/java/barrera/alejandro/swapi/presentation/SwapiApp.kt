package barrera.alejandro.swapi.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.components.AlertPopup
import barrera.alejandro.swapi.presentation.components.MarketingColumn
import barrera.alejandro.swapi.presentation.components.MarketingImage
import barrera.alejandro.swapi.presentation.components.TopBar
import barrera.alejandro.swapi.presentation.navigation.Category
import barrera.alejandro.swapi.presentation.navigation.SwapiNavKey
import barrera.alejandro.swapi.presentation.navigation.SwapiNavigation
import barrera.alejandro.swapi.presentation.navigation.SwapiNavigator
import barrera.alejandro.swapi.presentation.navigation.rememberSwapiNavBackStack
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.SwapiTheme

@Composable
fun SwapiApp(onInfoClick: () -> Unit) {
    val backStack = rememberSwapiNavBackStack(Category)

    SwapiApp(
        backStack = backStack,
        onInfoClick = onInfoClick,
    )
}

@Composable
internal fun SwapiApp(
    backStack: NavBackStack<SwapiNavKey>,
    onInfoClick: () -> Unit,
) {
    val navigator = remember(backStack) {
        SwapiNavigator(backStack)
    }

    var showDietitianPopup by rememberSaveable {
        mutableStateOf(false)
    }

    val canNavigateBack = backStack.size > 1

    SwapiTheme {
        val colorVariants = LocalColorVariants.current

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopBar(
                    canNavigateBack = canNavigateBack,
                    onBackClick = navigator::goBack,
                    onDietitianClick = {
                        showDietitianPopup = true
                    },
                    onInfoClick = onInfoClick,
                    onResetClick = navigator::resetToRoot,
                )
            },
        ) { paddingValues ->
            if (showDietitianPopup) {
                AlertPopup(
                    onDismiss = {
                        showDietitianPopup = false
                    },
                    icon = {
                        MarketingImage(
                            R.drawable.monica_picture,
                        )
                    },
                    buttonResourceId = R.string.back,
                    body = {
                        MarketingColumn(
                            text = stringResource(
                                id = R.string.dietitian_popup_text,
                            ),
                            instagramUrl = stringResource(
                                id = R.string.monica_instagram_url,
                            ),
                            email = stringResource(
                                id = R.string.email_address,
                            ),
                        )
                    },
                    containerColor = colorVariants.white,
                    contentColor = colorVariants.black,
                    titleResourceId =
                        R.string.dietitian_popup_title,
                )
            }

            SwapiNavigation(
                backStack = backStack,
                navigator = navigator,
                modifier = Modifier.padding(paddingValues),
            )
        }
    }
}