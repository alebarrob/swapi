package barrera.alejandro.swapi.food_swap.presentation.food_amount_selection_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.base.BaseScreen
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
import kotlinx.coroutines.flow.flowOf

@Composable
fun FoodAmountSelectionScreen(
    state: FoodAmountSelectionScreenState,
    onAmountChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val colorVariants = LocalColorVariants.current

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
                text = stringResource(id = R.string.food_selection_screen_message).toBoldColoredAnnotatedString(
                    mapOf(stringResource(id = R.string.bold_colored_calculate_equivalences) to colorVariants.darkGreen)),
                imageResourceId = R.drawable.surprised_watermelon_ic,
                imagePosition = ImagePosition.START
            )
        }
        item {
            FoodAmountCard(
                food = state.food,
                amount = state.amount,
                onAmountChange = onAmountChange
            )
        }
        item {
            ActionButton(
                text = stringResource(id = R.string.food_amount_selection_screen_button_text),
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun FoodAmountSelectionScreenPreview() {
    var amount by remember { mutableStateOf("") }

    val dummyState = FoodAmountSelectionScreenState(
        food = FoodUi(
            id = 1,
            name = "Arándanos",
            imageResourceId = R.drawable.blueberry_ic,
            conversionAmount = "120",
            categoryUi = CategoryUi(
                id = 1,
                name ="Frutas"
            ),
            unitUi = UnitUi(
                id = 1,
                name = "gr."
            )
        ),
        amount = amount
    )

    BaseScreen(uiEvent = flowOf()) {
        FoodAmountSelectionScreen(
            state = dummyState,
            onAmountChange = { amount = it }
        )
    }
}