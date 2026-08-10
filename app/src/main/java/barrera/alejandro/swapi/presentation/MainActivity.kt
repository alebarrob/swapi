package barrera.alejandro.swapi.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
            SwapiApp(
                onInfoClick = {
                    UserMessagingPlatform.showPrivacyOptionsForm(this) { error ->
                        Log.d(
                            PRIVACY_OPTIONS_FORM_ERROR,
                            error?.message ?: UNKNOWN_ERROR,
                        )
                    }
                },
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
                UserMessagingPlatform
                    .loadAndShowConsentFormIfRequired(this) { error ->
                        Log.d(
                            CONSENT_FORM_ERROR,
                            error?.message ?: UNKNOWN_ERROR,
                        )
                    }
            },
            { error ->
                Log.e(
                    CONSENT_INFO_UPDATE_ERROR,
                    error.message ?: UNKNOWN_ERROR,
                )
            },
        )
    }

    private companion object {
        const val CONSENT_FORM_ERROR = "ConsentFormError"
        const val CONSENT_INFO_UPDATE_ERROR = "ConsentInfoUpdateError"
        const val PRIVACY_OPTIONS_FORM_ERROR = "PrivacyOptionsFormError"
        const val UNKNOWN_ERROR = "Unknown error"
    }
}