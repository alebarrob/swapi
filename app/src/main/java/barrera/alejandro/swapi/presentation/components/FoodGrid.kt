package barrera.alejandro.swapi.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.components.preview.FoodGridPreviewParameterProvider
import barrera.alejandro.swapi.presentation.components.preview.FoodGridPreviewState
import barrera.alejandro.swapi.presentation.model.FoodUi
import barrera.alejandro.swapi.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.util.constant.PREVIEW_BACKGROUND

@Composable
fun FoodGrid(
    onClick: (Int) -> Unit,
    foods: List<FoodUi>,
    modifier: Modifier = Modifier,
    withResult: Boolean = false,
) {
    val dimensions = LocalDimensions.current

    LazyVerticalGrid(
        columns = GridCells.Adaptive(
            minSize = dimensions.imageCardSize,
        ),
        modifier = modifier,
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
                        food.name,
                    )
                } else {
                    food.name
                },
                imageResourceId = food.imageResourceId,
                withHighlightImage = withResult,
                modifier = Modifier.padding(
                    dimensions.extraSmall,
                ),
            )
        }
    }
}

@Composable
fun FoodGrid(
    foods: List<FoodUi>,
    modifier: Modifier = Modifier,
    withResult: Boolean = false,
) {
    val dimensions = LocalDimensions.current

    LazyVerticalGrid(
        columns = GridCells.Adaptive(
            minSize = dimensions.imageCardSize,
        ),
        modifier = modifier,
    ) {
        items(items = foods) { food ->
            ImageCard(
                text = if (withResult) {
                    stringResource(
                        id = R.string.food_result,
                        food.equivalentAmount,
                        food.unitUi.name,
                        food.name,
                    )
                } else {
                    food.name
                },
                imageResourceId = food.imageResourceId,
                withHighlightImage = withResult,
                modifier = Modifier.padding(
                    dimensions.extraSmall,
                ),
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND,
)
@Composable
private fun FoodGridPreview(
    @PreviewParameter(FoodGridPreviewParameterProvider::class)
    previewState: FoodGridPreviewState,
) {
    SwapiTheme {
        FoodGrid(
            onClick = {},
            foods = previewState.foods,
            withResult = previewState.withResult,
        )
    }
}