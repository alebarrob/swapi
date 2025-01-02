package barrera.alejandro.swapi.presentation.food_result_screen

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.swapi.BuildConfig
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.MainActivity
import barrera.alejandro.swapi.presentation.base.BaseScreen
import barrera.alejandro.swapi.presentation.components.FailureScreen
import barrera.alejandro.swapi.presentation.components.FoodGrid
import barrera.alejandro.swapi.presentation.components.InformationCard
import barrera.alejandro.swapi.presentation.components.LoadingScreen
import barrera.alejandro.swapi.presentation.model.CategoryUi
import barrera.alejandro.swapi.presentation.model.FoodUi
import barrera.alejandro.swapi.presentation.model.UnitUi
import barrera.alejandro.swapi.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.presentation.util.enums.ImagePosition
import barrera.alejandro.swapi.presentation.util.extension.toBoldColoredAnnotatedString
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.ump.UserMessagingPlatform
import kotlinx.coroutines.flow.flowOf

private const val TAG_ADMOB = "AdMob"
private const val MSG_AD_CLICKED = "The user clicked on the ad"
private const val MSG_AD_CLOSED = "The ad was closed"
private const val MSG_AD_FAILED_TO_SHOW = "Failed to show the ad"
private const val MSG_AD_IMPRESSION = "The ad recorded an impression"
private const val MSG_AD_SHOWN = "The ad was shown in fullscreen"
private const val MSG_AD_FAILED_TO_LOAD = "Failed to load the ad"

@Composable
fun FoodResultScreen(
    modifier: Modifier = Modifier,
    viewModel: FoodResultViewModel = hiltViewModel<FoodResultViewModel>()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        if (UserMessagingPlatform.getConsentInformation(context).canRequestAds()) {
            showInterstitialAd(
                context = context,
                onAdLoaded = {
                    viewModel.onEvent(FoodResultScreenEvent.LoadEquivalentFood)
                },
                onAdFailed = {
                    viewModel.onEvent(FoodResultScreenEvent.LoadEquivalentFood)
                }
            )
        } else {
            viewModel.onEvent(FoodResultScreenEvent.LoadEquivalentFood)
        }

    }

    BaseScreen(uiEvent = viewModel.uiEvent) {
        when (val state = viewModel.state) {
            is FoodResultScreenState.Loading -> LoadingScreen(modifier = modifier)

            is FoodResultScreenState.Success -> SuccessFoodResultScreen(
                state = state,
                modifier = modifier
            )

            is FoodResultScreenState.Failure -> FailureScreen(modifier = modifier)
        }
    }
}

@Composable
private fun SuccessFoodResultScreen(
    state: FoodResultScreenState.Success,
    modifier: Modifier = Modifier
) {
    val orientation = LocalConfiguration.current.orientation
    val dimensions = LocalDimensions.current
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = dimensions.large,
                end = dimensions.large,
                top = dimensions.large
            ),
        verticalArrangement = Arrangement.spacedBy(dimensions.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
            InformationCard(
                text = stringResource(
                    id = R.string.food_result_screen_message,
                    state.discardedFoodAmount,
                    state.discardedFood.unitUi.name,
                    state.discardedFood.name
                ).toBoldColoredAnnotatedString(
                    mapOf(
                        stringResource(
                            id = R.string.bold_colored_food_result_screen_message,
                            state.discardedFoodAmount,
                            state.discardedFood.unitUi.name,
                            state.discardedFood.name
                        ) to colors.secondary
                    )
                ),
                decorativeImageResourceId = R.drawable.wow_watermelon_ic,
                highlightImageResourceId = state.discardedFood.imageResourceId,
                imagePosition = ImagePosition.HIGHLIGHT_ON_START
            )
        }
        FoodGrid(
            foods = state.equivalentFoods,
            withResult = true
        )
    }
}

private fun showInterstitialAd(
    context: Context,
    onAdLoaded: () -> Unit,
    onAdFailed: () -> Unit,
) {
    val adRequest = AdRequest.Builder().build()
    var interstitialAd: InterstitialAd?

    InterstitialAd.load(
        context,
        BuildConfig.INTERSTITIAL_AD_ID,
        adRequest,
        object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(ad: InterstitialAd) {
                interstitialAd = ad
                ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdClicked() {
                        Log.d(TAG_ADMOB, MSG_AD_CLICKED)
                    }

                    override fun onAdDismissedFullScreenContent() {
                        Log.d(TAG_ADMOB, MSG_AD_CLOSED)
                        interstitialAd = null // Release the resource
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        Log.e(TAG_ADMOB, "$MSG_AD_FAILED_TO_SHOW: ${adError.message}")
                        interstitialAd = null
                    }

                    override fun onAdImpression() {
                        Log.d(TAG_ADMOB, MSG_AD_IMPRESSION)
                    }

                    override fun onAdShowedFullScreenContent() {
                        Log.d(TAG_ADMOB, MSG_AD_SHOWN)
                    }
                }
                if (context is MainActivity) interstitialAd?.show(context)
                onAdLoaded()
            }

            override fun onAdFailedToLoad(error: LoadAdError) {
                Log.e(TAG_ADMOB, "$MSG_AD_FAILED_TO_LOAD: ${error.message}")
                interstitialAd = null
                onAdFailed()
            }
        }
    )
}

@Preview
@Composable
private fun SuccessFoodResultScreenPreview() {
    SwapiTheme {
        BaseScreen(uiEvent = flowOf()) {
            SuccessFoodResultScreen(
                state = FoodResultScreenState.Success(
                    discardedFood = FoodUi(
                        id = 19,
                        name = "Pera",
                        imageResourceId = R.drawable.pear_ic,
                        standardAmount = "160",
                        equivalentAmount = "20",
                        categoryUi = CategoryUi(
                            id = 1,
                            name ="Frutas",
                            conversionFactor = 130.0
                        ),
                        unitUi = UnitUi(
                            id = 1,
                            name = "gr."
                        )
                    ),
                    discardedFoodAmount = "20",
                    equivalentFoods = listOf(
                        FoodUi(
                            id = 1,
                            name = "Arándanos",
                            imageResourceId = R.drawable.blueberry_ic,
                            standardAmount = "120",
                            equivalentAmount = "20",
                            categoryUi = CategoryUi(
                                id = 1,
                                name ="Frutas",
                                conversionFactor = 130.0
                            ),
                            unitUi = UnitUi(
                                id = 1,
                                name = "gr."
                            )
                        ),
                        FoodUi(
                            id = 2,
                            name = "Cerezas",
                            imageResourceId = R.drawable.cherry_ic,
                            standardAmount = "145",
                            equivalentAmount = "20",
                            categoryUi = CategoryUi(
                                id = 1,
                                name ="Frutas",
                                conversionFactor = 130.0
                            ),
                            unitUi = UnitUi(
                                id = 1,
                                name = "gr."
                            )
                        ),
                        FoodUi(
                            id = 1,
                            name = "Ciruelas",
                            imageResourceId = R.drawable.plum_ic,
                            standardAmount = "145",
                            equivalentAmount = "20",
                            categoryUi = CategoryUi(
                                id = 1,
                                name ="Frutas",
                                conversionFactor = 130.0
                            ),
                            unitUi = UnitUi(
                                id = 1,
                                name = "gr."
                            )
                        ),
                        FoodUi(
                            id = 1,
                            name = "Dátiles",
                            imageResourceId = R.drawable.date_ic,
                            standardAmount = "20",
                            equivalentAmount = "20",
                            categoryUi = CategoryUi(
                                id = 1,
                                name ="Frutas",
                                conversionFactor = 130.0
                            ),
                            unitUi = UnitUi(
                                id = 1,
                                name = "gr."
                            )
                        ),
                        FoodUi(
                            id = 1,
                            name = "Frambuesas",
                            imageResourceId = R.drawable.raspberry_ic,
                            standardAmount = "200",
                            equivalentAmount = "20",
                            categoryUi = CategoryUi(
                                id = 1,
                                name ="Frutas",
                                conversionFactor = 130.0
                            ),
                            unitUi = UnitUi(
                                id = 1,
                                name = "gr."
                            )
                        ),
                        FoodUi(
                            id = 1,
                            name = "Fresas",
                            imageResourceId = R.drawable.strawberry_ic,
                            standardAmount = "250",
                            equivalentAmount = "20",
                            categoryUi = CategoryUi(
                                id = 1,
                                name ="Frutas",
                                conversionFactor = 130.0
                            ),
                            unitUi = UnitUi(
                                id = 1,
                                name = "gr."
                            )
                        ),
                        FoodUi(
                            id = 1,
                            name = "Higos",
                            imageResourceId = R.drawable.fig_ic,
                            standardAmount = "160",
                            equivalentAmount = "20",
                            categoryUi = CategoryUi(
                                id = 1,
                                name ="Frutas",
                                conversionFactor = 130.0
                            ),
                            unitUi = UnitUi(
                                id = 1,
                                name = "gr."
                            )
                        ),
                        FoodUi(
                            id = 1,
                            name = "Kiwi",
                            imageResourceId = R.drawable.kiwi_ic,
                            standardAmount = "140",
                            equivalentAmount = "20",
                            categoryUi = CategoryUi(
                                id = 1,
                                name ="Frutas",
                                conversionFactor = 130.0
                            ),
                            unitUi = UnitUi(
                                id = 1,
                                name = "gr."
                            )
                        ),
                        FoodUi(
                            id = 1,
                            name = "Mandarinas",
                            imageResourceId = R.drawable.tangerine_ic,
                            standardAmount = "170",
                            equivalentAmount = "20",
                            categoryUi = CategoryUi(
                                id = 1,
                                name ="Frutas",
                                conversionFactor = 130.0
                            ),
                            unitUi = UnitUi(
                                id = 1,
                                name = "gr."
                            )
                        )
                    )
                )
            )
        }
    }
}