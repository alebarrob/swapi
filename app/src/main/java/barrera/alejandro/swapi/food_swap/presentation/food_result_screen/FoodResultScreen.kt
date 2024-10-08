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
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.base.BaseScreen
import barrera.alejandro.swapi.core.presentation.components.InformationCard
import barrera.alejandro.swapi.core.presentation.components.LoadableContent
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.core.presentation.util.enums.ImagePosition
import barrera.alejandro.swapi.core.presentation.util.extension.toBoldColoredAnnotatedString
import barrera.alejandro.swapi.food_swap.presentation.components.FoodGrid
import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi
import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi
import barrera.alejandro.swapi.food_swap.presentation.model.UnitUi
import kotlinx.coroutines.flow.flowOf

@Composable
fun FoodResultScreen(
    modifier: Modifier = Modifier,
    viewModel: FoodResultViewModel = hiltViewModel<FoodResultViewModel>()
) {
    val orientation = LocalConfiguration.current.orientation
    val dimensions = LocalDimensions.current
    val colors = MaterialTheme.colorScheme

    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(FoodResultScreenEvent.LoadEquivalentFood)
    }

    BaseScreen(
        modifier = modifier,
        uiEvent = viewModel.uiEvent
    ) {
        LoadableContent(isLoading = state.isLoading) {
            Column(
                modifier = Modifier
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
                    state.discardedFood?.let { food ->
                        InformationCard(
                            text = stringResource(
                                id = R.string.food_result_screen_message,
                                state.discardedFoodAmount,
                                food.unitUi.name,
                                food.name
                            ).toBoldColoredAnnotatedString(
                                mapOf(
                                    stringResource(
                                        id = R.string.bold_colored_food_result_screen_message,
                                        state.discardedFoodAmount,
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
                    foods = state.equivalentFoods,
                    withResult = true
                )
            }
        }
    }
}

@Preview
@Composable
private fun FoodResultScreenPreview(
    modifier: Modifier = Modifier,
    state: FoodResultScreenState = FoodResultScreenState(
        discardedFood = FoodUi(
            id = 19,
            name = "Pera",
            imageResourceId = R.drawable.pear_ic,
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
        discardedFoodAmount = "20",
        equivalentFoods = listOf(
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
    )
) {
    SwapiTheme {
        val orientation = LocalConfiguration.current.orientation
        val dimensions = LocalDimensions.current
        val colors = MaterialTheme.colorScheme

        BaseScreen(
            modifier = modifier,
            uiEvent = flowOf()
        ) {
            LoadableContent(isLoading = state.isLoading) {
                Column(
                    modifier = Modifier
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
                        state.discardedFood?.let { food ->
                            InformationCard(
                                text = stringResource(
                                    id = R.string.food_result_screen_message,
                                    state.discardedFoodAmount,
                                    food.unitUi.name,
                                    food.name
                                ).toBoldColoredAnnotatedString(
                                    mapOf(
                                        stringResource(
                                            id = R.string.bold_colored_food_result_screen_message,
                                            state.discardedFoodAmount,
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
                        foods = state.equivalentFoods,
                        withResult = true
                    )
                }
            }
        }
    }

}