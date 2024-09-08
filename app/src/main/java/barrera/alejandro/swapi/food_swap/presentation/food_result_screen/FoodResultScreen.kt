package barrera.alejandro.swapi.food_swap.presentation.food_result_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.base.BaseScreen
import barrera.alejandro.swapi.core.presentation.components.InformationCard
import barrera.alejandro.swapi.core.presentation.components.LoadableContent
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.util.enums.ImagePosition
import barrera.alejandro.swapi.core.presentation.util.extension.toBoldColoredAnnotatedString
import barrera.alejandro.swapi.food_swap.presentation.components.FoodGrid
import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi
import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi
import barrera.alejandro.swapi.food_swap.presentation.model.UnitUi
import kotlinx.coroutines.flow.flowOf

@Composable
fun FoodResultScreen(
    state: FoodResultScreenState,
    onEvent: (FoodResultScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val orientation = LocalConfiguration.current.orientation
    val colors = MaterialTheme.colorScheme

    LaunchedEffect(key1 = Unit) {
        onEvent(FoodResultScreenEvent.LoadEquivalentFood)
    }

    LoadableContent(isLoading = state.isLoading) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    start = dimensions.large,
                    end = dimensions.large,
                    top = dimensions.large
                ),
            verticalArrangement = Arrangement.spacedBy(dimensions.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
                state.selectedFood?.let { food ->
                    InformationCard(
                        text = stringResource(
                            id = R.string.food_result_screen_message,
                            state.selectedAmount,
                            food.unitUi.name,
                            food.name
                        ).toBoldColoredAnnotatedString(
                            mapOf(
                                stringResource(
                                    id = R.string.bold_colored_food_result_screen_message,
                                    state.selectedAmount,
                                    food.unitUi.name,
                                    food.name
                                ) to colors.secondary
                            )
                        ),
                        decorativeImageResourceId = R.drawable.wow_watermelon_ic,
                        highlightImageResourceId = food.imageResourceId,
                        imagePosition = ImagePosition.HIGHLIGHT_ON_START
                    )
                }
            }
            FoodGrid(
                onClick = {},
                food = state.equivalentFood,
                withResult = true
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFoodResultScreen() {
    val dummyState = FoodResultScreenState(
        selectedFood = FoodUi(
            id = 19,
            name = "Pera",
            imageResourceId = R.drawable.pear_ic,
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
        selectedAmount = "20",
        equivalentFood = listOf(
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
    )

    BaseScreen(uiEvent = flowOf()) {
        FoodResultScreen(
            state = dummyState,
            onEvent = {}
        )
    }
}