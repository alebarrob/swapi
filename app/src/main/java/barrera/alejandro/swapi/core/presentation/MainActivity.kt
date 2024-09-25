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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barrera.alejandro.swapi.core.presentation.components.TopBar
import barrera.alejandro.swapi.core.presentation.navigation.Category
import barrera.alejandro.swapi.core.presentation.navigation.NavGraph
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            SwapiApp(navController)
        }
    }
}

@Composable
fun SwapiApp(navController: NavHostController) {
    val route = navController.currentBackStackEntryAsState().value?.destination?.route
    val categoryScreenRoute = "barrera.alejandro.swapi.core.presentation.navigation.Category"
    var topBarIsVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(route) {
        topBarIsVisible = route != categoryScreenRoute
    }

    SwapiTheme {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopBar(
                    isVisible = topBarIsVisible,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onResetClick = {
                        navController.popBackStack(route = Category, inclusive = false)
                    }
                )
            }
        ) { paddingValues ->
            NavGraph(
                navController = navController,
                modifier = if (topBarIsVisible) Modifier.padding(paddingValues) else Modifier
            )
        }
    }
}