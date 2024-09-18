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
import barrera.alejandro.swapi.core.util.constant.PREVIEW_BACKGROUND
import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi
import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi
import barrera.alejandro.swapi.food_swap.presentation.model.UnitUi

@Composable
fun FoodGrid(
    onClick: (Int) -> Unit,
    foods: List<FoodUi>,
    modifier: Modifier = Modifier,
    withResult: Boolean = false
) {
    val dimensions = LocalDimensions.current

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = dimensions.imageCardSize),
        modifier = modifier
    ) {
        items(items = foods) { food ->
            ImageCard(
                onClick = {
                    onClick(food.id)
                },
                text = if (withResult) {
                    stringResource(
                        id = R.string.food_result,
                        food.equivalentAmount,
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

@Composable
fun FoodGrid(
    foods: List<FoodUi>,
    modifier: Modifier = Modifier,
    withResult: Boolean = false
) {
    val dimensions = LocalDimensions.current

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = dimensions.imageCardSize),
        modifier = modifier
    ) {
        items(items = foods) { food ->
            ImageCard(
                text = if (withResult) {
                    stringResource(
                        id = R.string.food_result,
                        food.equivalentAmount,
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
                standardAmount = "120",
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

        FoodGrid(
            onClick = {},
            foods = food
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

        FoodGrid(
            onClick = {},
            foods = food,
            withResult = true
        )
    }
}