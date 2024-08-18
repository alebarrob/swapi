package barrera.alejandro.swapi.food_swap.presentation.category_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.base.BaseScreen
import barrera.alejandro.swapi.core.presentation.components.InformationCard
import barrera.alejandro.swapi.core.presentation.components.LoadableContent
import barrera.alejandro.swapi.core.presentation.theme.LocalColorVariants
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.util.enums.ImagePosition
import barrera.alejandro.swapi.core.presentation.util.extension.toBoldColoredAnnotatedString
import barrera.alejandro.swapi.food_swap.presentation.components.DropDownButton
import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi
import kotlinx.coroutines.flow.flowOf

@Composable
fun CategoryScreen(
    state: CategoryScreenState,
    onEvent: (CategoryScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val colorVariants = LocalColorVariants.current
    val dimensions = LocalDimensions.current

    LaunchedEffect(key1 = Unit) {
        onEvent(CategoryScreenEvent.LoadCategories)
    }

    LoadableContent(isLoading = state.isLoading) {
        Column(
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
            InformationCard(
                text = stringResource(id = R.string.categories_screen_message).toBoldColoredAnnotatedString(
                    mapOf(
                        stringResource(id = R.string.bold_colored_swapi) to colors.secondary,
                        stringResource(id = R.string.bold_colored_category) to colorVariants.darkGreen
                    )
                ),
                imageResourceId = R.drawable.happy_watermelon_ic,
                imagePosition = ImagePosition.END
            )
            DropDownButton(
                text = stringResource(id = R.string.categories_screen_button_text),
                options = state.categories.map { it.name },
                onOptionClick = {  }
            )
        }
    }
}

@Preview
@Composable
fun PreviewCategoryScreen() {
    val dummyState = CategoryScreenState(
        categories = listOf(
            CategoryUi(id = 0, name = "FRUTAS"),
            CategoryUi(id = 1, name = "GRASAS Y PROTEÍNAS"),
            CategoryUi(id = 2, name = "GRASAS"),
            CategoryUi(id = 3, name = "CARBOHIDRATOS"),
            CategoryUi(id = 4, name = "LÁCTEOS")
        )
    )

    BaseScreen(uiEvent = flowOf()) {
        CategoryScreen(
            state = dummyState,
            onEvent = { }
        )
    }
}