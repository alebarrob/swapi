package barrera.alejandro.swapi.presentation.food_selection

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.components.FailureScreen
import barrera.alejandro.swapi.presentation.components.FoodGrid
import barrera.alejandro.swapi.presentation.components.InformationCard
import barrera.alejandro.swapi.presentation.components.LoadingScreen
import barrera.alejandro.swapi.presentation.components.VerticalGradientBackground
import barrera.alejandro.swapi.presentation.enums.ImagePosition
import barrera.alejandro.swapi.presentation.food_selection.FoodSelectionContract.State
import barrera.alejandro.swapi.presentation.food_selection.preview.FoodSelectionStatePreviewParameterProvider
import barrera.alejandro.swapi.presentation.model.FoodUi
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.util.constant.GRADIENT_START_FRACTION

@Composable
fun FoodSelectionScreen(
    onFoodClick: (foodId: Int) -> Unit,
    viewModel: FoodSelectionViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FoodSelectionScreen(
        state = state,
        onFoodClick = onFoodClick,
        modifier = modifier,
    )
}

@Composable
fun FoodSelectionScreen(
    state: State,
    onFoodClick: (foodId: Int) -> Unit,
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
                SuccessFoodSelectionContent(
                    foods = state.foods,
                    onFoodClick = onFoodClick,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun SuccessFoodSelectionContent(
    foods: List<FoodUi>,
    onFoodClick: (foodId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val orientation = LocalConfiguration.current.orientation
    val dimensions = LocalDimensions.current

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
                    id = R.string.food_selection_screen_message,
                ),
                decorativeImageResourceId = R.drawable.question_watermelon_ic,
                imagePosition = ImagePosition.HIGHLIGHT_ON_START,
            )
        }

        FoodGrid(
            foods = foods,
            onClick = onFoodClick,
        )
    }
}

@Preview
@Composable
private fun FoodSelectionScreenPreview(
    @PreviewParameter(FoodSelectionStatePreviewParameterProvider::class)
    state: State,
) {
    SwapiTheme {
        FoodSelectionScreen(
            state = state,
            onFoodClick = {},
        )
    }
}