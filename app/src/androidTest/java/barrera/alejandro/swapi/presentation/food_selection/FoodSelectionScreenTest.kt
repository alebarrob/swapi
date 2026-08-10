package barrera.alejandro.swapi.presentation.food_selection

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import barrera.alejandro.swapi.presentation.food_selection.FoodSelectionContract.State
import barrera.alejandro.swapi.presentation.model.PresentationTestData
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FoodSelectionScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun foodClickReturnsSelectedFoodId() {
        var selectedFoodId: Int? = null

        composeTestRule.setContent {
            SwapiTheme {
                FoodSelectionScreen(
                    state = State.Success(
                        foods = listOf(
                            PresentationTestData.blueberry,
                            PresentationTestData.cherry,
                        ),
                    ),
                    onFoodClick = { foodId ->
                        selectedFoodId = foodId
                    },
                )
            }
        }

        composeTestRule
            .onNodeWithText("Arándanos")
            .performClick()

        composeTestRule.runOnIdle {
            assertEquals(
                PresentationTestData.blueberry.id,
                selectedFoodId,
            )
        }
    }
}