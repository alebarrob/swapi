package barrera.alejandro.swapi.food_swap.presentation.category_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.base.BaseScreen
import barrera.alejandro.swapi.core.presentation.components.InformationCard
import barrera.alejandro.swapi.core.presentation.components.LoadableContent
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.core.presentation.util.enums.ImagePosition
import barrera.alejandro.swapi.core.presentation.util.extension.toBoldColoredAnnotatedString
import barrera.alejandro.swapi.food_swap.presentation.components.DropDownButton
import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi
import kotlinx.coroutines.flow.flowOf

@Composable
fun CategoryScreen(
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel<CategoryViewModel>(),
) {
    val dimensions = LocalDimensions.current
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current

    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(CategoryScreenEvent.LoadCategories)
    }

    BaseScreen(
        modifier = modifier,
        uiEvent = viewModel.uiEvent
    ) {
        LoadableContent(isLoading = state.isLoading) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = dimensions.large,
                        end = dimensions.large,
                        top = dimensions.screenPaddingTop
                    ),
                verticalArrangement = Arrangement.spacedBy(dimensions.extraLarge),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    InformationCard(
                        text = stringResource(id = R.string.categories_screen_message)
                            .toBoldColoredAnnotatedString(
                                mapOf(
                                    stringResource(id = R.string.bold_colored_swapi) to colors.secondary,
                                    stringResource(id = R.string.bold_colored_category) to colorVariants.darkGreen
                                )
                            ),
                        decorativeImageResourceId = R.drawable.happy_watermelon_ic,
                        imagePosition = ImagePosition.HIGHLIGHT_ON_START
                    )
                }
                item {
                    DropDownButton(
                        text = stringResource(id = R.string.categories_screen_button_text),
                        options = state.categories.map { category ->
                            category.name
                        },
                        onOptionClick = { option ->
                            state.categories
                                .find { category ->
                                    category.name == option
                                }
                                ?.let { category ->
                                    onCategoryClick(category.id)
                                }
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCategoryScreen(
    modifier: Modifier = Modifier,
    onCategoryClick: (Int) -> Unit = { },
    state: CategoryScreenState = CategoryScreenState(
        categories = listOf(
            CategoryUi(id = 0, name = "FRUTAS", conversionFactor = 130.0),
            CategoryUi(id = 1, name = "GRASAS Y PROTEÍNAS", conversionFactor = 110.0),
            CategoryUi(id = 2, name = "GRASAS", conversionFactor = 50.0),
            CategoryUi(id = 3, name = "CARBOHIDRATOS", conversionFactor = 40.0),
            CategoryUi(id = 4, name = "LÁCTEOS", conversionFactor = 100.0)
        )
    )
) {
    SwapiTheme {
        val dimensions = LocalDimensions.current
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current

        BaseScreen(
            modifier = modifier,
            uiEvent = flowOf()
        ) {
            LoadableContent(isLoading = state.isLoading) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = dimensions.large,
                            end = dimensions.large,
                            top = dimensions.screenPaddingTop
                        ),
                    verticalArrangement = Arrangement.spacedBy(dimensions.extraLarge),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        InformationCard(
                            text = stringResource(id = R.string.categories_screen_message)
                                .toBoldColoredAnnotatedString(
                                    mapOf(
                                        stringResource(id = R.string.bold_colored_swapi) to colors.secondary,
                                        stringResource(id = R.string.bold_colored_category) to colorVariants.darkGreen
                                    )
                                ),
                            decorativeImageResourceId = R.drawable.happy_watermelon_ic,
                            imagePosition = ImagePosition.HIGHLIGHT_ON_START
                        )
                    }
                    item {
                        DropDownButton(
                            text = stringResource(id = R.string.categories_screen_button_text),
                            options = state.categories.map { category ->
                                category.name
                            },
                            onOptionClick = { option ->
                                state.categories
                                    .find { category ->
                                        category.name == option
                                    }
                                    ?.let { category ->
                                        onCategoryClick(category.id)
                                    }
                            }
                        )
                    }
                }
            }
        }
    }
}