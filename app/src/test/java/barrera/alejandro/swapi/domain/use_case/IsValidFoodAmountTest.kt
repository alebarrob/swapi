package barrera.alejandro.swapi.domain.use_case

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class IsValidFoodAmountTest {

    private val isValidFoodAmount = IsValidFoodAmount()

    @Test
    fun `returns true for integer amount`() {
        assertTrue(isValidFoodAmount("42"))
    }

    @Test
    fun `returns true for decimal amount with point`() {
        assertTrue(isValidFoodAmount("42.42"))
    }

    @Test
    fun `returns true for decimal amount with comma`() {
        assertTrue(isValidFoodAmount("42,42"))
    }

    @Test
    fun `returns false for non numeric amount`() {
        assertFalse(isValidFoodAmount("hello"))
    }

    @Test
    fun `returns false for amount with multiple decimal separators`() {
        assertFalse(isValidFoodAmount("42.42.42"))
    }

    @Test
    fun `returns false for zero`() {
        assertFalse(isValidFoodAmount("0"))
    }

    @Test
    fun `returns false for negative amount`() {
        assertFalse(isValidFoodAmount("-42"))
    }

    @Test
    fun `returns false for empty amount`() {
        assertFalse(isValidFoodAmount(""))
    }
}