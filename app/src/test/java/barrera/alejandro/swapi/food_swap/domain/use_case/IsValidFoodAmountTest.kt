package barrera.alejandro.swapi.food_swap.domain.use_case

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class IsValidFoodAmountTest {

    private lateinit var isValidFoodAmount: IsValidFoodAmount

    @Before
    fun setUp() {
        isValidFoodAmount = IsValidFoodAmount()
    }

    @Test
    fun `test IsValidFoodAmount returns true on integer number input`() {
        val expected = true
        val actual = isValidFoodAmount.invoke(IsValidFoodAmount.Params(amount = "42"))

        assertEquals(expected, actual)
    }

    @Test
    fun `test IsValidFoodAmount returns true on decimal number input`() {
        val expected = true
        val actual = isValidFoodAmount.invoke(IsValidFoodAmount.Params(amount = "42.42"))

        assertEquals(expected, actual)
    }

    @Test
    fun `test IsValidFoodAmount returns false on non number input`() {
        val expected = false
        val actual = isValidFoodAmount.invoke(IsValidFoodAmount.Params(amount = "hello"))

        assertEquals(expected, actual)
    }

    @Test
    fun `test IsValidFoodAmount returns false on number with more than one decimal point input`() {
        val expected = false
        val actual = isValidFoodAmount.invoke(IsValidFoodAmount.Params(amount = "42.42.42"))

        assertEquals(expected, actual)
    }
}