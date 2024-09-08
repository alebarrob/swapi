package barrera.alejandro.swapi.food_swap.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.core.presentation.util.constant.PREVIEW_BACKGROUND
import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi
import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi
import barrera.alejandro.swapi.food_swap.presentation.model.UnitUi

@Composable
fun FoodGrid(
    onClick: (String) -> Unit,
    food: List<FoodUi>,
    modifier: Modifier = Modifier,
    withResult: Boolean = false
) {
    val dimensions = LocalDimensions.current

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = dimensions.imageCardSize),
        modifier = modifier
    ) {
        items(items = food) { food ->
            ImageCard(
                onClick = onClick,
                text = if (withResult) {
                    stringResource(
                        id = R.string.food_result,
                        food.resultAmount,
                        food.unitUi.name,
                        food.name
                    )
                } else food.name,
                imageResourceId = food.imageResourceId,
                withHighlightImage = withResult,
                modifier = Modifier.padding(dimensions.extraSmall)
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
private fun PreviewFoodGrid() {
    SwapiTheme {
        val food = listOf(
            FoodUi(
                id = 1,
                name = "Arándanos",
                imageResourceId = R.drawable.blueberry_ic,
                conversionAmount = "120",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "145",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "145",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "20",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "200",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "250",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "160",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "140",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "170",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
                ),
                unitUi = UnitUi(
                    id = 1,
                    name = "gr."
                )
            )
        )

        FoodGrid(
            onClick = {},
            food = food
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
private fun PreviewFoodGridWithResult() {
    SwapiTheme {
        val food = listOf(
            FoodUi(
                id = 1,
                name = "Arándanos",
                imageResourceId = R.drawable.blueberry_ic,
                conversionAmount = "120",
                resultAmount = "20",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "145",
                resultAmount = "20",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "145",
                resultAmount = "20",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "20",
                resultAmount = "20",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "200",
                resultAmount = "20",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "250",
                resultAmount = "20",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "160",
                resultAmount = "20",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "140",
                resultAmount = "20",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
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
                conversionAmount = "170",
                resultAmount = "20",
                categoryUi = CategoryUi(
                    id = 1,
                    name ="Frutas",
                    referenceAmount = 130.0
                ),
                unitUi = UnitUi(
                    id = 1,
                    name = "gr."
                )
            )
        )

        FoodGrid(
            onClick = {},
            food = food,
            withResult = true
        )
    }
}