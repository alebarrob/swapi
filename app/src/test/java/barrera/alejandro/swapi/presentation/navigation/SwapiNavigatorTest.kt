package barrera.alejandro.swapi.presentation.navigation

import org.junit.Assert.assertEquals
import org.junit.Test

class SwapiNavigatorTest {

    @Test
    fun `navigate adds destination to back stack`() {
        val backStack = mutableListOf<SwapiNavKey>(Category)
        val navigator = SwapiNavigator(backStack)

        navigator.navigate(destination = FoodSelection(categoryId = 1))

        assertEquals(
            listOf(Category, FoodSelection(categoryId = 1)),
            backStack,
        )
    }

    @Test
    fun `goBack removes current destination`() {
        val backStack = mutableListOf(Category, FoodSelection(categoryId = 1))
        val navigator = SwapiNavigator(backStack)

        navigator.goBack()

        assertEquals(
            listOf(Category),
            backStack,
        )
    }

    @Test
    fun `goBack does not remove root destination`() {
        val backStack = mutableListOf<SwapiNavKey>(Category)
        val navigator = SwapiNavigator(backStack)

        navigator.goBack()

        assertEquals(
            listOf(Category),
            backStack,
        )
    }

    @Test
    fun `resetToRoot removes all destinations except root`() {
        val backStack = mutableListOf(
            Category,
            FoodSelection(categoryId = 1),
            FoodAmountSelection(foodId = 1),
            FoodResult(foodId = 1, amount = "25"),
        )
        val navigator = SwapiNavigator(backStack)

        navigator.resetToRoot()

        assertEquals(
            listOf(Category),
            backStack,
        )
    }
}