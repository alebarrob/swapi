package barrera.alejandro.swapi.food_swap.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi
import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi
import barrera.alejandro.swapi.food_swap.presentation.model.UnitUi

@Composable
fun FoodAmountCard(
    food: FoodUi,
    amount: String,
    onAmountChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        ImageCard(
            text = food.name,
            imageResourceId = food.imageResourceId
        )
        AmountTextField(
            unit = food.unitUi,
            amount = amount,
            onAmountChange = onAmountChange
        )
    }
}

@Preview
@Composable
private fun FoodAmountCardPreview(

) {
    val food = FoodUi(
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
    )
    var amount by remember { mutableStateOf("") }

    SwapiTheme {
        FoodAmountCard(
            food = food,
            amount = amount,
            onAmountChange = { amount = it }
        )
    }
}