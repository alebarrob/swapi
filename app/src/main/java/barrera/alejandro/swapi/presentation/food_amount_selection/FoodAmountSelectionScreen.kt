package barrera.alejandro.swapi.presentation.food_amount_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.components.ActionButton
import barrera.alejandro.swapi.presentation.components.FailureScreen
import barrera.alejandro.swapi.presentation.components.FoodAmountCard
import barrera.alejandro.swapi.presentation.components.InformationCard
import barrera.alejandro.swapi.presentation.components.LoadingScreen
import barrera.alejandro.swapi.presentation.components.VerticalGradientBackground
import barrera.alejandro.swapi.presentation.enums.ImagePosition
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionContract.Action
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionContract.Effect
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionContract.State
import barrera.alejandro.swapi.presentation.food_amount_selection.preview.FoodAmountSelectionStatePreviewParameterProvider
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.util.constant.GRADIENT_START_FRACTION
import barrera.alejandro.swapi.util.extension.toBoldColoredAnnotatedString

@Composable
fun FoodAmountSelectionScreen(
    onCalculateClick: (foodId: Int, amount: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FoodAmountSelectionViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(viewModel, lifecycle) {
        viewModel.effect
            .flowWithLifecycle(
                lifecycle = lifecycle,
                minActiveState = Lifecycle.State.STARTED,
            )
            .collect { effect ->
                when (effect) {
                    is Effect.NavigateToEquivalences -> {
                        onCalculateClick(
                            effect.foodId,
                            effect.amount,
                        )
                    }
                }
            }
    }

    FoodAmountSelectionScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier,
    )
}

@Composable
fun FoodAmountSelectionScreen(
    state: State,
    onAction: (Action) -> Unit,
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
                SuccessFoodAmountSelectionContent(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun SuccessFoodAmountSelectionContent(
    state: State.Success,
    onAction: (Action) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimensions = LocalDimensions.current
    val colorVariants = LocalColorVariants.current

    LazyColumn(
        modifier = modifier
            .padding(
                start = dimensions.large,
                end = dimensions.large,
                top = dimensions.large,
            )
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(dimensions.large),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            InformationCard(
                text = stringResource(
                    id = R.string.food_amount_selection_screen_message,
                ).toBoldColoredAnnotatedString(
                    chunksToStyle = mapOf(
                        stringResource(
                            id = R.string.bold_colored_calculate_equivalences,
                        ) to colorVariants.darkGreen,
                    ),
                ),
                decorativeImageResourceId = R.drawable.surprised_watermelon_ic,
                imagePosition = ImagePosition.DECORATIVE_ON_START,
            )
        }

        item {
            FoodAmountCard(
                food = state.food,
                amount = state.form.amount,
                onAmountChange = { amount ->
                    onAction(
                        Action.AmountChanged(
                            amount = amount,
                        )
                    )
                },
                isError = state.form.amountHasError,
                errorText = if (state.form.amountHasError) {
                    stringResource(id = R.string.invalid_food_amount_error)
                } else {
                    null
                },
            )
        }

        item {
            ActionButton(
                text = stringResource(
                    id = R.string.food_amount_selection_screen_button_text,
                ),
                onClick = {
                    onAction(Action.CalculateClicked)
                },
            )
        }
    }
}

@Preview
@Composable
private fun FoodAmountSelectionScreenPreview(
    @PreviewParameter(FoodAmountSelectionStatePreviewParameterProvider::class)
    state: State,
) {
    SwapiTheme {
        FoodAmountSelectionScreen(
            state = state,
            onAction = {},
        )
    }
}