package barrera.alejandro.swapi.presentation.food_result

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.ad.showInterstitialAd
import barrera.alejandro.swapi.presentation.components.FailureScreen
import barrera.alejandro.swapi.presentation.components.FoodGrid
import barrera.alejandro.swapi.presentation.components.InformationCard
import barrera.alejandro.swapi.presentation.components.LoadingScreen
import barrera.alejandro.swapi.presentation.components.VerticalGradientBackground
import barrera.alejandro.swapi.presentation.enums.ImagePosition
import barrera.alejandro.swapi.presentation.food_result.FoodResultContract.Action
import barrera.alejandro.swapi.presentation.food_result.FoodResultContract.AdState
import barrera.alejandro.swapi.presentation.food_result.FoodResultContract.State
import barrera.alejandro.swapi.presentation.food_result.preview.FoodResultStatePreviewParameterProvider
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.util.constant.GRADIENT_START_FRACTION
import barrera.alejandro.swapi.util.extension.toBoldColoredAnnotatedString

@Composable
fun FoodResultScreen(
    modifier: Modifier = Modifier,
    viewModel: FoodResultViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val currentState = state

    LaunchedEffect(viewModel) {
        viewModel.initialize()
    }

    if (currentState is State.Success) {
        LaunchedEffect(currentState.adState) {
            if (currentState.adState == AdState.Required) {
                showInterstitialAd(
                    context = context,
                    onFinished = {
                        viewModel.onAction(Action.AdFinished)
                    },
                )
            }
        }
    }

    FoodResultScreen(
        state = state,
        modifier = modifier,
    )
}

@Composable
fun FoodResultScreen(
    state: State,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current
    val windowHeightPx = LocalWindowInfo.current.containerSize.height

    VerticalGradientBackground(
        colors = listOf(
            colorVariants.lightGreen,
            colors.primary,
        ),
        startY = windowHeightPx * GRADIENT_START_FRACTION,
        modifier = modifier.fillMaxSize(),
    ) {
        when (state) {
            State.Loading -> {
                LoadingScreen(modifier = Modifier.fillMaxSize())
            }

            State.Failure -> {
                FailureScreen(modifier = Modifier.fillMaxSize())
            }

            is State.Success -> {
                when (state.adState) {
                    AdState.Checking,
                    AdState.Required -> {
                        LoadingScreen(modifier = Modifier.fillMaxSize())
                    }

                    AdState.Completed -> {
                        SuccessFoodResultContent(
                            state = state,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SuccessFoodResultContent(
    state: State.Success,
    modifier: Modifier = Modifier,
) {
    val orientation = LocalConfiguration.current.orientation
    val dimensions = LocalDimensions.current
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = modifier.padding(
            start = dimensions.large,
            end = dimensions.large,
            top = dimensions.large,
        ),
        verticalArrangement = Arrangement.spacedBy(dimensions.small),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
            InformationCard(
                text = stringResource(
                    id = R.string.food_result_screen_message,
                    state.discardedFoodAmount,
                    state.discardedFood.unitUi.name,
                    state.discardedFood.name,
                ).toBoldColoredAnnotatedString(
                    chunksToStyle = mapOf(
                        stringResource(
                            id = R.string.bold_colored_food_result_screen_message,
                            state.discardedFoodAmount,
                            state.discardedFood.unitUi.name,
                            state.discardedFood.name,
                        ) to colors.secondary,
                    ),
                ),
                decorativeImageResourceId = R.drawable.wow_watermelon_ic,
                highlightImageResourceId = state.discardedFood.imageResourceId,
                imagePosition = ImagePosition.HIGHLIGHT_ON_START,
            )
        }

        FoodGrid(
            foods = state.equivalentFoods,
            withResult = true,
        )
    }
}

@Preview
@Composable
private fun FoodResultScreenPreview(
    @PreviewParameter(FoodResultStatePreviewParameterProvider::class)
    state: State,
) {
    SwapiTheme {
        FoodResultScreen(state = state)
    }
}