package barrera.alejandro.swapi.presentation.food_result

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import barrera.alejandro.swapi.presentation.food_result.FoodResultContract.AdState
import barrera.alejandro.swapi.presentation.food_result.FoodResultContract.State
import barrera.alejandro.swapi.presentation.model.PresentationTestData
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import org.junit.Rule
import org.junit.Test

class FoodResultScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun completedAdStateShowsEquivalentFoods() {
        composeTestRule.setContent {
            SwapiTheme {
                FoodResultScreen(state = successState(adState = AdState.Completed))
            }
        }

        composeTestRule
            .onNodeWithText("30.21 gr. de Cerezas")
            .assertExists()
    }

    @Test
    fun checkingAdStateDoesNotShowEquivalentFoods() {
        composeTestRule.setContent {
            SwapiTheme {
                FoodResultScreen(
                    state = successState(adState = AdState.Checking),
                )
            }
        }

        composeTestRule
            .onNodeWithText("30.21 gr. de Cerezas")
            .assertDoesNotExist()
    }

    @Test
    fun requiredAdStateDoesNotShowEquivalentFoods() {
        composeTestRule.setContent {
            SwapiTheme {
                FoodResultScreen(
                    state = successState(adState = AdState.Required),
                )
            }
        }

        composeTestRule
            .onNodeWithText("30.21 gr. de Cerezas")
            .assertDoesNotExist()
    }

    private fun successState(
        adState: AdState,
    ) = State.Success(
        discardedFood = PresentationTestData.blueberry,
        discardedFoodAmount = "25",
        equivalentFoods = listOf(PresentationTestData.cherry),
        adState = adState,
    )
}