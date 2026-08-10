package barrera.alejandro.swapi.presentation.food_result.preview

import androidx.annotation.DrawableRes
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.food_result.FoodResultContract.AdState
import barrera.alejandro.swapi.presentation.food_result.FoodResultContract.State
import barrera.alejandro.swapi.presentation.model.CategoryUi
import barrera.alejandro.swapi.presentation.model.FoodUi
import barrera.alejandro.swapi.presentation.model.UnitUi

internal class FoodResultStatePreviewParameterProvider :
    PreviewParameterProvider<State> {

    private val states = listOf(
        State.Loading,
        FoodResultPreviewData.successState,
        State.Failure,
    )

    override val values: Sequence<State> = states.asSequence()

    override fun getDisplayName(index: Int): String? =
        when (states.getOrNull(index)) {
            State.Loading -> "Loading"
            is State.Success -> "Success"
            State.Failure -> "Failure"
            null -> null
        }
}

private object FoodResultPreviewData {

    private val fruitCategory = CategoryUi(
        id = 1,
        name = "Frutas",
    )

    private val gramsUnit = UnitUi(
        id = 1,
        name = "gr.",
    )

    private val discardedFood = createFood(
        id = 19,
        name = "Pera",
        imageResourceId = R.drawable.pear_ic,
        standardAmount = "160",
        equivalentAmount = "20",
    )

    private val equivalentFoods = listOf(
        createFood(
            id = 1,
            name = "Arándanos",
            imageResourceId = R.drawable.blueberry_ic,
            standardAmount = "120",
            equivalentAmount = "15",
        ),
        createFood(
            id = 2,
            name = "Cerezas",
            imageResourceId = R.drawable.cherry_ic,
            standardAmount = "145",
            equivalentAmount = "18",
        ),
        createFood(
            id = 3,
            name = "Ciruelas",
            imageResourceId = R.drawable.plum_ic,
            standardAmount = "145",
            equivalentAmount = "18",
        ),
        createFood(
            id = 4,
            name = "Dátiles",
            imageResourceId = R.drawable.date_ic,
            standardAmount = "20",
            equivalentAmount = "2.5",
        ),
        createFood(
            id = 5,
            name = "Frambuesas",
            imageResourceId = R.drawable.raspberry_ic,
            standardAmount = "200",
            equivalentAmount = "25",
        ),
        createFood(
            id = 6,
            name = "Fresas",
            imageResourceId = R.drawable.strawberry_ic,
            standardAmount = "250",
            equivalentAmount = "31",
        ),
        createFood(
            id = 7,
            name = "Higos",
            imageResourceId = R.drawable.fig_ic,
            standardAmount = "160",
            equivalentAmount = "20",
        ),
        createFood(
            id = 8,
            name = "Kiwi",
            imageResourceId = R.drawable.kiwi_ic,
            standardAmount = "140",
            equivalentAmount = "18",
        ),
        createFood(
            id = 9,
            name = "Mandarinas",
            imageResourceId = R.drawable.tangerine_ic,
            standardAmount = "170",
            equivalentAmount = "21",
        ),
    )

    val successState = State.Success(
        discardedFood = discardedFood,
        discardedFoodAmount = "20",
        equivalentFoods = equivalentFoods,
        adState = AdState.Completed,
    )

    private fun createFood(
        id: Int,
        name: String,
        @DrawableRes imageResourceId: Int,
        standardAmount: String,
        equivalentAmount: String,
    ) = FoodUi(
        id = id,
        name = name,
        imageResourceId = imageResourceId,
        standardAmount = standardAmount,
        equivalentAmount = equivalentAmount,
        categoryUi = fruitCategory,
        unitUi = gramsUnit,
    )
}