package barrera.alejandro.swapi.presentation.food_amount_selection_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.base.BaseScreen
import barrera.alejandro.swapi.presentation.components.ActionButton
import barrera.alejandro.swapi.presentation.components.FailureScreen
import barrera.alejandro.swapi.presentation.components.FoodAmountCard
import barrera.alejandro.swapi.presentation.components.InformationCard
import barrera.alejandro.swapi.presentation.components.LoadingScreen
import barrera.alejandro.swapi.presentation.model.CategoryUi
import barrera.alejandro.swapi.presentation.model.FoodUi
import barrera.alejandro.swapi.presentation.model.UnitUi
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.presentation.enums.ImagePosition
import barrera.alejandro.swapi.util.extension.toBoldColoredAnnotatedString
import kotlinx.coroutines.flow.flowOf

@Composable
fun FoodAmountSelectionScreen(
    onCalculateClick: (Int, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FoodAmountSelectionViewModel = hiltViewModel<FoodAmountSelectionViewModel>()
) {
    BaseScreen(uiEvent = viewModel.uiEvent) {
        when (val state = viewModel.state) {
            is FoodAmountSelectionScreenState.Loading -> LoadingScreen(modifier = modifier)

            is FoodAmountSelectionScreenState.Success -> SuccessFoodAmountSelectionScreen(
                state = state,
                onCalculateClick = onCalculateClick,
                isValidFoodAmount = viewModel::isValidFoodAmount,
                onInvalidFoodAmount = {
                    viewModel.onEvent(FoodAmountSelectionScreenEvent.InvalidFoodAmount)
                },
                modifier = modifier
            )

            is FoodAmountSelectionScreenState.Failure -> FailureScreen(modifier = modifier)
        }
    }
}

@Composable
fun SuccessFoodAmountSelectionScreen(
    state: FoodAmountSelectionScreenState.Success,
    onCalculateClick: (Int, String) -> Unit,
    isValidFoodAmount: (String) -> Boolean,
    onInvalidFoodAmount: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val colorVariants = LocalColorVariants.current

    var amount by rememberSaveable { mutableStateOf("") }
    var amountHasError by rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = dimensions.large,
                end = dimensions.large,
                top = dimensions.large
            )
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(dimensions.large),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            InformationCard(
                text = stringResource(id = R.string.food_amount_selection_screen_message).toBoldColoredAnnotatedString(
                    mapOf(stringResource(id = R.string.bold_colored_calculate_equivalences) to colorVariants.darkGreen)),
                decorativeImageResourceId = R.drawable.surprised_watermelon_ic,
                imagePosition = ImagePosition.DECORATIVE_ON_START
            )
        }
        item {
            FoodAmountCard(
                food = state.food,
                amount = amount,
                onAmountChange = {
                    amount = it
                },
                isError = amountHasError
            )
        }
        item {
            ActionButton(
                text = stringResource(id = R.string.food_amount_selection_screen_button_text),
                onClick = {
                    if (isValidFoodAmount(amount)) {
                        amountHasError = false
                        onCalculateClick(state.food.id, amount)
                    } else {
                        amountHasError = true
                        onInvalidFoodAmount()
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSuccessFoodAmountSelectionScreenPreview() {
    SwapiTheme {
        BaseScreen(uiEvent = flowOf()) {
            SuccessFoodAmountSelectionScreen(
                state = FoodAmountSelectionScreenState.Success(
                    food = FoodUi(
                        id = 1,
                        name = "Fresas",
                        imageResourceId = R.drawable.strawberry_ic,
                        standardAmount = "250",
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
                ),
                onCalculateClick = { _, _ -> },
                isValidFoodAmount = { _ -> true },
                onInvalidFoodAmount = {}
            )
        }
    }
}