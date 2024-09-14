package barrera.alejandro.swapi.food_swap.presentation.category_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.base.BaseScreen
import barrera.alejandro.swapi.core.presentation.base.UiEvent
import barrera.alejandro.swapi.core.presentation.components.InformationCard
import barrera.alejandro.swapi.core.presentation.components.LoadableContent
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.util.enums.ImagePosition
import barrera.alejandro.swapi.core.presentation.util.extension.toBoldColoredAnnotatedString
import barrera.alejandro.swapi.food_swap.presentation.components.DropDownButton
import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel,
    onCategoryClick: (Int) -> Unit,
    onShowErrorPopup: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val dimensions = LocalDimensions.current
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current

    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(CategoryScreenEvent.LoadCategories)

        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowErrorPopup -> onShowErrorPopup()
                is UiEvent.ShowToast -> Toast.makeText(
                    context,
                    event.message.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    LoadableContent(isLoading = state.isLoading) {
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

@Preview
@Composable
private fun PreviewCategoryScreen(
    modifier: Modifier = Modifier,
    onCategoryClick: (Int) -> Unit = { },
    state: CategoryScreenState = CategoryScreenState(
        categories = listOf(
            CategoryUi(id = 0, name = "FRUTAS", referenceAmount = 130.0),
            CategoryUi(id = 1, name = "GRASAS Y PROTEÍNAS", referenceAmount = 110.0),
            CategoryUi(id = 2, name = "GRASAS", referenceAmount = 50.0),
            CategoryUi(id = 3, name = "CARBOHIDRATOS", referenceAmount = 40.0),
            CategoryUi(id = 4, name = "LÁCTEOS", referenceAmount = 100.0)
        )
    )
) {
    BaseScreen(
        onErrorPopupDismiss = {},
        showErrorPopup = false
    ) {
        val dimensions = LocalDimensions.current
        val colors = MaterialTheme.colorScheme
        val colorVariants = LocalColorVariants.current

        LoadableContent(isLoading = state.isLoading) {
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