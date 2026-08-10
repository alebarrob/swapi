package barrera.alejandro.swapi.presentation.ad

import android.content.Context
import barrera.alejandro.swapi.BuildConfig
import barrera.alejandro.swapi.presentation.MainActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.ump.UserMessagingPlatform

fun showInterstitialAd(
    context: Context,
    onFinished: () -> Unit,
) {
    val activity = context as? MainActivity

    if (
        activity == null ||
        !UserMessagingPlatform.getConsentInformation(context).canRequestAds()
    ) {
        onFinished()
        return
    }

    InterstitialAd.load(
        activity,
        BuildConfig.INTERSTITIAL_AD_ID,
        AdRequest.Builder().build(),
        object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(ad: InterstitialAd) {
                ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        onFinished()
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        onFinished()
                    }
                }

                ad.show(activity)
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                onFinished()
            }
        },
    )
}