package barrera.alejandro.swapi.presentation.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.category.CategoryContract.State
import barrera.alejandro.swapi.presentation.category.preview.CategoryStatePreviewParameterProvider
import barrera.alejandro.swapi.presentation.components.DropDownButton
import barrera.alejandro.swapi.presentation.components.FailureScreen
import barrera.alejandro.swapi.presentation.components.InformationCard
import barrera.alejandro.swapi.presentation.components.LoadingScreen
import barrera.alejandro.swapi.presentation.components.VerticalGradientBackground
import barrera.alejandro.swapi.presentation.enums.ImagePosition
import barrera.alejandro.swapi.presentation.model.CategoryUi
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.util.constant.GRADIENT_START_FRACTION
import barrera.alejandro.swapi.util.extension.toBoldColoredAnnotatedString

@Composable
fun CategoryScreen(
    onNavigateToFoodSelection: (categoryId: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CategoryScreen(
        state = state,
        onCategoryClick = onNavigateToFoodSelection,
        modifier = modifier,
    )
}

@Composable
fun CategoryScreen(
    state: State,
    onCategoryClick: (categoryId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val windowHeightPx = LocalWindowInfo.current.containerSize.height
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current

    VerticalGradientBackground(
        colors = listOf(
            colorVariants.lightGreen,
            colors.primary,
        ),
        startY = windowHeightPx * GRADIENT_START_FRACTION,
        modifier = modifier.fillMaxSize(),
    ) {
        when (state) {
            State.Loading -> {
                LoadingScreen(modifier = Modifier.fillMaxSize())
            }

            State.Failure -> {
                FailureScreen(modifier = Modifier.fillMaxSize())
            }

            is State.Success -> {
                SuccessCategoryContent(
                    categories = state.categories,
                    onCategoryClick = onCategoryClick,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun SuccessCategoryContent(
    categories: List<CategoryUi>,
    onCategoryClick: (categoryId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimensions = LocalDimensions.current
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current

    LazyColumn(
        modifier = modifier.padding(
            start = dimensions.large,
            end = dimensions.large,
            top = dimensions.screenPaddingTop,
        ),
        verticalArrangement = Arrangement.spacedBy(dimensions.extraLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            InformationCard(
                text = stringResource(
                    id = R.string.categories_screen_message,
                ).toBoldColoredAnnotatedString(
                    chunksToStyle = mapOf(
                        stringResource(
                            id = R.string.bold_colored_swapi,
                        ) to colors.secondary,
                        stringResource(
                            id = R.string.bold_colored_category,
                        ) to colorVariants.darkGreen,
                    ),
                ),
                decorativeImageResourceId = R.drawable.happy_watermelon_ic,
                imagePosition = ImagePosition.HIGHLIGHT_ON_START,
            )
        }

        item {
            DropDownButton(
                text = stringResource(
                    id = R.string.categories_screen_button_text,
                ),
                options = categories.map { category ->
                    category.name
                },
                onOptionClick = { selectedCategoryName ->
                    categories
                        .firstOrNull { category ->
                            category.name == selectedCategoryName
                        }
                        ?.let { selectedCategory ->
                            onCategoryClick(selectedCategory.id)
                        }
                },
            )
        }
    }
}

@Preview
@Composable
private fun CategoryScreenPreview(
    @PreviewParameter(CategoryStatePreviewParameterProvider::class)
    state: State,
) {
    SwapiTheme {
        CategoryScreen(
            state = state,
            onCategoryClick = {},
        )
    }
}