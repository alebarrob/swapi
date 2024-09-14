package barrera.alejandro.swapi.food_swap.presentation.food_selection_screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.base.BaseScreen
import barrera.alejandro.swapi.core.presentation.base.UiEvent
import barrera.alejandro.swapi.core.presentation.components.InformationCard
import barrera.alejandro.swapi.core.presentation.components.LoadableContent
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.util.enums.ImagePosition
import barrera.alejandro.swapi.food_swap.presentation.components.FoodGrid
import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi
import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi
import barrera.alejandro.swapi.food_swap.presentation.model.UnitUi

@Composable
fun FoodSelectionScreen(
    onFoodClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FoodSelectionViewModel
) {
    val context = LocalContext.current
    val orientation = LocalConfiguration.current.orientation
    val dimensions = LocalDimensions.current

    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(FoodSelectionScreenEvent.LoadFood)

        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowPopup -> {}
                is UiEvent.ShowToast -> Toast.makeText(
                    context,
                    event.message.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
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
                InformationCard(
                    text = stringResource(id = R.string.food_selection_screen_message),
                    decorativeImageResourceId = R.drawable.question_watermelon_ic,
                    imagePosition = ImagePosition.HIGHLIGHT_ON_START
                )
            }
            FoodGrid(
                onClick = { foodId ->
                    onFoodClick(foodId)
                },
                food = state.food
            )
        }
    }
}

@Preview
@Composable
private fun FoodSelectionScreenPreview(
    modifier: Modifier = Modifier,
    onFoodClick: (Int) -> Unit = { },
    state: FoodSelectionScreenState = FoodSelectionScreenState(
        food = listOf(
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
    )
) {
    BaseScreen {
        val orientation = LocalConfiguration.current.orientation
        val dimensions = LocalDimensions.current

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
                    InformationCard(
                        text = stringResource(id = R.string.food_selection_screen_message),
                        decorativeImageResourceId = R.drawable.question_watermelon_ic,
                        imagePosition = ImagePosition.HIGHLIGHT_ON_START
                    )
                }
                FoodGrid(
                    onClick = { foodId ->
                        onFoodClick(foodId)
                    },
                    food = state.food
                )
            }
        }
    }
}