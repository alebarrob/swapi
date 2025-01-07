package barrera.alejandro.swapi.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import barrera.alejandro.swapi.presentation.components.TopBar
import barrera.alejandro.swapi.presentation.navigation.Category
import barrera.alejandro.swapi.presentation.navigation.NavGraph
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import com.google.android.gms.ads.MobileAds
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var consentInformation: ConsentInformation
    private lateinit var params: ConsentRequestParameters

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        showAdsConsentForm()
        setContent {
            val navController = rememberNavController()

            SwapiApp(
                onInfoClick = {
                    UserMessagingPlatform.showPrivacyOptionsForm(this) { error ->
                        Log.d(PRIVACY_OPTIONS_FORM_ERROR, error?.message ?: UNKNOWN_ERROR)
                    }
                },
                navController = navController
            )
        }
    }

    private fun showAdsConsentForm() {
        consentInformation = UserMessagingPlatform.getConsentInformation(this)
        params = ConsentRequestParameters.Builder().build()

        consentInformation.requestConsentInfoUpdate(
            this,
            params,
            {
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(this) { error ->
                    Log.d(CONSENT_FORM_ERROR, error?.message ?: UNKNOWN_ERROR)
                }
            },
            { error ->
                Log.e(CONSENT_INFO_UPDATE_ERROR, error.message ?: UNKNOWN_ERROR)
            }
        )
    }

    companion object {
        private const val CONSENT_FORM_ERROR = "ConsentFormError"
        private const val CONSENT_INFO_UPDATE_ERROR = "ConsentInfoUpdateError"
        private const val PRIVACY_OPTIONS_FORM_ERROR = "PrivacyOptionsFormError"
        private const val UNKNOWN_ERROR = "Unknown error"
    }
}

@Composable
fun SwapiApp(
    onInfoClick: () -> Unit,
    navController: NavHostController
) {
    val route = navController.currentBackStackEntryAsState().value?.destination?.route

    SwapiTheme {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                route?.let {
                    TopBar(
                        route = route,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onResetClick = {
                            navController.popBackStack(route = Category, inclusive = false)
                        },
                        onInfoClick = onInfoClick
                    )
                }
            }
        ) { paddingValues ->
            NavGraph(
                navController = navController,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}