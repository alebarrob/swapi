package barrera.alejandro.swapi.food_swap.presentation.food_amount_selection_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.base.BaseScreen
import barrera.alejandro.swapi.core.presentation.base.UiEvent
import barrera.alejandro.swapi.core.presentation.components.InformationCard
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.util.enums.ImagePosition
import barrera.alejandro.swapi.core.presentation.util.extension.toBoldColoredAnnotatedString
import barrera.alejandro.swapi.food_swap.presentation.components.ActionButton
import barrera.alejandro.swapi.food_swap.presentation.components.FoodAmountCard
import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi
import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi
import barrera.alejandro.swapi.food_swap.presentation.model.UnitUi

@Composable
fun FoodAmountSelectionScreen(
    viewModel: FoodAmountSelectionViewModel,
    onCalculateClick: (Int, String) -> Unit,
    onShowErrorPopup: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val dimensions = LocalDimensions.current
    val colorVariants = LocalColorVariants.current

    val state = viewModel.state

    var amount by rememberSaveable { mutableStateOf("") }
    var amountHasError by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(FoodAmountSelectionScreenEvent.LoadFood)

        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowErrorPopup -> onShowErrorPopup()
                is UiEvent.ShowToast -> Toast.makeText(
                    context,
                    event.message.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

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
        state.food?.let { food ->
            item {
                FoodAmountCard(
                    food = food,
                    amount = amount,
                    onAmountChange = {
                        amount = it
                    },
                    isError = amountHasError
                )
            }
        }
        item {
            ActionButton(
                text = stringResource(id = R.string.food_amount_selection_screen_button_text),
                onClick = {
                    state.food?.let { food ->
                        if (viewModel.isFoodAmountValid(amount)) {
                            amountHasError = false
                            onCalculateClick(food.id, amount)
                        } else {
                            amountHasError = true
                            viewModel.onEvent(FoodAmountSelectionScreenEvent.InvalidFoodAmount)
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun FoodAmountSelectionScreenPreview(
    modifier: Modifier = Modifier,
    onCalculateClick: (Int, String) -> Unit = { _, _ -> },
    state: FoodAmountSelectionScreenState = FoodAmountSelectionScreenState(
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
        ),
        isLoading = false
    )
) {
    BaseScreen(
        onErrorPopupDismiss = {},
        showErrorPopup = false
    ) {
        val dimensions = LocalDimensions.current
        val colorVariants = LocalColorVariants.current

        var amount by rememberSaveable { mutableStateOf("") }

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
            state.food?.let { food ->
                item {
                    FoodAmountCard(
                        food = food,
                        amount = amount,
                        onAmountChange = { amount = it }
                    )
                }
            }
            item {
                ActionButton(
                    text = stringResource(id = R.string.food_amount_selection_screen_button_text),
                    onClick = {
                        state.food?.let { food ->
                            onCalculateClick(food.id, amount)
                        }
                    }
                )
            }
        }
    }
}