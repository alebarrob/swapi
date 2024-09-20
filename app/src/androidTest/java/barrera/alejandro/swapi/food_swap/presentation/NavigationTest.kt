package barrera.alejandro.swapi.food_swap.presentation

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import barrera.alejandro.swapi.core.presentation.MainActivity
import barrera.alejandro.swapi.core.presentation.SwapiApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltRule.inject()

        val scenario = ActivityScenario.launch(MainActivity::class.java)

        scenario.onActivity { activity ->
            navController = TestNavHostController(activity)
            navController.setLifecycleOwner(activity)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            activity.setContent {
                SwapiApp(navController = navController)
            }
        }
    }

    @Test
    fun navigateToFoodResultScreen() {
        with(composeTestRule) {
            onNodeWithText("CATEGORÍAS").performClick()
            onNodeWithText("FRUTAS").performClick()
            onNodeWithText("Arándanos").performClick()
            onNodeWithText("gr.").performTextInput("25")
            onNodeWithText("¡Calcular Equivalencias!").performClick()
            onNodeWithText("30.21 gr. de Cerezas").assertExists()
        }
    }

    @Test
    fun navigateBack() {
        with(composeTestRule) {
            onNodeWithText("CATEGORÍAS").performClick()
            onNodeWithText("FRUTAS").performClick()
            onNodeWithContentDescription("Icono de flecha atrás").performClick()
            onNodeWithText("CATEGORÍAS").assertExists()
        }
    }

    @Test
    fun resetNavigation() {
        with(composeTestRule) {
            onNodeWithText("CATEGORÍAS").performClick()
            onNodeWithText("FRUTAS").performClick()
            onNodeWithText("Arándanos").performClick()
            onNodeWithText("gr.").performTextInput("25")
            onNodeWithText("¡Calcular Equivalencias!").performClick()
            onNodeWithText("30.21 gr. de Cerezas").assertExists()
            onNodeWithContentDescription("Icono de reset").performClick()
            onNodeWithText("CATEGORÍAS").assertExists()
        }
    }
}