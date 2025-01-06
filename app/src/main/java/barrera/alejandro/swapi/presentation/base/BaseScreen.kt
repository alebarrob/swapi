package barrera.alejandro.swapi.presentation.base

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import barrera.alejandro.swapi.BuildConfig
import barrera.alejandro.swapi.presentation.MainActivity
import barrera.alejandro.swapi.presentation.components.VerticalGradientBackground
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.ump.UserMessagingPlatform
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

private const val HALF_DIVISOR = 2

@Composable
fun BaseScreen(
    uiEvent: Flow<UiEvent>,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenDensity = LocalDensity.current
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current

    LaunchedEffect(key1 = Unit) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> Toast.makeText(
                    context,
                    event.message.asString(context),
                    Toast.LENGTH_SHORT
                ).show()

                is UiEvent.ShowAd -> showInterstitialAd(
                    context = context,
                    onAdDismissed = event.onAdDismissed
                )
            }
        }
    }

    VerticalGradientBackground(
        colors = listOf(colorVariants.lightGreen, colors.primary),
        startY = with(screenDensity) {
            configuration.screenHeightDp.dp.toPx() / HALF_DIVISOR
        }
    ) {
        content()
    }
}

private fun showInterstitialAd(
    context: Context,
    onAdDismissed: () -> Unit
) {
    if (context is MainActivity && UserMessagingPlatform.getConsentInformation(context).canRequestAds()) {
        InterstitialAd.load(
            context,
            BuildConfig.INTERSTITIAL_AD_ID,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                        override fun onAdClicked() {}

                        override fun onAdDismissedFullScreenContent() {
                            onAdDismissed()
                        }

                        override fun onAdFailedToShowFullScreenContent(error: AdError) {
                            onAdDismissed()
                        }

                        override fun onAdImpression() {}

                        override fun onAdShowedFullScreenContent() {}
                    }
                    ad.show(context)
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    onAdDismissed()
                }
            }
        )
    } else {
        onAdDismissed()
    }
}

@Preview
@Composable
private fun BaseScreenPreview() {
    SwapiTheme {
        BaseScreen(uiEvent = flowOf()) { }
    }
}