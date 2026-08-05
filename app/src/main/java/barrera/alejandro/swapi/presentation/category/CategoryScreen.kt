package barrera.alejandro.swapi.presentation.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.base.BaseScreen
import barrera.alejandro.swapi.presentation.components.DropDownButton
import barrera.alejandro.swapi.presentation.components.FailureScreen
import barrera.alejandro.swapi.presentation.components.InformationCard
import barrera.alejandro.swapi.presentation.components.LoadingScreen
import barrera.alejandro.swapi.presentation.model.CategoryUi
import barrera.alejandro.swapi.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.presentation.enums.ImagePosition
import barrera.alejandro.swapi.util.extension.toBoldColoredAnnotatedString
import kotlinx.coroutines.flow.flowOf

@Composable
fun CategoryScreen(
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel<CategoryViewModel>(),
) {
    BaseScreen(uiEvent = viewModel.uiEvent) {
        when (val state = viewModel.state) {
            is CategoryScreenState.Loading -> LoadingScreen(modifier = modifier)

            is CategoryScreenState.Success -> SuccessCategoryScreen(
                state = state,
                onCategoryClick = onCategoryClick
            )

            is CategoryScreenState.Failure -> FailureScreen(modifier = modifier)
        }
    }
}

@Composable
private fun SuccessCategoryScreen(
    state: CategoryScreenState.Success,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current

    LazyColumn(
        modifier = modifier
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
                onOptionClick = { categoryName ->
                    state.categories
                        .find { category ->
                            category.name == categoryName
                        }
                        ?.let { category ->
                            onCategoryClick(category.id)
                        }
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSuccessCategoryScreen() {
    SwapiTheme {
        BaseScreen(uiEvent = flowOf()) {
            SuccessCategoryScreen(
                state = CategoryScreenState.Success(
                    categories = listOf(
                        CategoryUi(id = 0, name = "FRUTAS"),
                        CategoryUi(id = 1, name = "PROTEÍNAS"),
                        CategoryUi(id = 2, name = "GRASAS"),
                        CategoryUi(id = 3, name = "CARBOHIDRATOS"),
                        CategoryUi(id = 4, name = "LÁCTEOS")
                    )
                ),
                onCategoryClick = {}
            )
        }
    }
}