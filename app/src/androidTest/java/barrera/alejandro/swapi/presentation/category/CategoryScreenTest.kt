package barrera.alejandro.swapi.presentation.category

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.category.CategoryContract.State
import barrera.alejandro.swapi.presentation.model.PresentationTestData
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CategoryScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun categoryClickReturnsSelectedCategoryId() {
        var selectedCategoryId: Int? = null

        composeTestRule.setContent {
            SwapiTheme {
                CategoryScreen(
                    state = State.Success(categories = listOf(PresentationTestData.fruitCategory)),
                    onCategoryClick = { categoryId ->
                        selectedCategoryId = categoryId
                    },
                )
            }
        }

        val categoriesButtonText = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .getString(R.string.categories_screen_button_text)

        composeTestRule
            .onNodeWithText(categoriesButtonText)
            .performClick()

        composeTestRule
            .onNodeWithText("FRUTAS")
            .performClick()

        composeTestRule.runOnIdle {
            assertEquals(
                PresentationTestData.fruitCategory.id,
                selectedCategoryId,
            )
        }
    }
}