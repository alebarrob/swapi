package barrera.alejandro.swapi.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.base.BaseScreen
import barrera.alejandro.swapi.core.presentation.components.TopBar
import barrera.alejandro.swapi.core.presentation.navigation.Category
import barrera.alejandro.swapi.core.presentation.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwapiApp()
        }
    }
}

@Composable
fun SwapiApp() {
    val navController = rememberNavController()
    val route = navController.currentBackStackEntryAsState().value?.destination?.route

    var topBarIsVisible by rememberSaveable { mutableStateOf(false) }

    var showErrorPopup by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(route) {
        topBarIsVisible = route != Category::class.java.name
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopBar(
                isVisible = topBarIsVisible,
                title = stringResource(id = R.string.back),
                onBackClick = {
                    navController.popBackStack()
                },
                onResetClick = {
                    navController.popBackStack(route = Category, inclusive = false)
                }
            )
        }
    ) { paddingValues ->
        BaseScreen(
            showErrorPopup = showErrorPopup,
            onErrorPopupDismiss = {
                showErrorPopup = false
            },
            modifier = if (topBarIsVisible) {
                Modifier.padding(paddingValues)
            } else {
                Modifier
            }
        ) {
            NavGraph(
                navController = navController,
                onShowErrorPopup = {
                    showErrorPopup = true
                }
            )
        }
    }
}
