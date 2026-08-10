package barrera.alejandro.swapi.presentation.food_amount_selection

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.test.platform.app.InstrumentationRegistry
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionContract.Action
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionContract.FormState
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionContract.State
import barrera.alejandro.swapi.presentation.model.PresentationTestData
import barrera.alejandro.swapi.presentation.theme.SwapiTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FoodAmountSelectionScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun amountChangeSendsAmountChangedAction() {
        var receivedAction: Action? = null

        composeTestRule.setContent {
            SwapiTheme {
                FoodAmountSelectionScreen(
                    state = successState(),
                    onAction = { action ->
                        receivedAction = action
                    },
                )
            }
        }

        composeTestRule
            .onNodeWithText("gr.")
            .performTextReplacement("25")

        composeTestRule.runOnIdle {
            assertEquals(
                Action.AmountChanged(amount = "25"),
                receivedAction,
            )
        }
    }

    @Test
    fun calculateClickSendsCalculateClickedAction() {
        var receivedAction: Action? = null

        composeTestRule.setContent {
            SwapiTheme {
                FoodAmountSelectionScreen(
                    state = successState(amount = "25"),
                    onAction = { action ->
                        receivedAction = action
                    },
                )
            }
        }

        val calculateButtonText = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .getString(R.string.food_amount_selection_screen_button_text)

        composeTestRule
            .onNodeWithText(calculateButtonText)
            .performClick()

        composeTestRule.runOnIdle {
            assertEquals(
                Action.CalculateClicked,
                receivedAction,
            )
        }
    }

    @Test
    fun invalidAmountShowsErrorMessage() {
        composeTestRule.setContent {
            SwapiTheme {
                FoodAmountSelectionScreen(
                    state = successState(
                        amount = "25,",
                        amountHasError = true,
                    ),
                    onAction = {},
                )
            }
        }

        val errorText = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .getString(R.string.invalid_food_amount_error)

        composeTestRule
            .onNodeWithText(errorText)
            .assertExists()
    }

    private fun successState(
        amount: String = "",
        amountHasError: Boolean = false,
    ) = State.Success(
        food = PresentationTestData.blueberry,
        form = FormState(
            amount = amount,
            amountHasError = amountHasError,
        ),
    )
}