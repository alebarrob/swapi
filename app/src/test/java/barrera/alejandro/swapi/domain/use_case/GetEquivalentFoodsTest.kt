package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.model.DomainTestData
import org.junit.Assert.assertEquals
import org.junit.Test

class GetEquivalentFoodsTest {

    private val getEquivalentFoods = GetEquivalentFoods()

    @Test
    fun `returns equivalent foods`() {
        val actual = getEquivalentFoods(
            discardedFood = DomainTestData.skimmedMilk,
            discardedFoodAmount = 25.0,
            replacementFoods = DomainTestData.replacementFoods,
        )

        assertEquals(
            DomainTestData.equivalentFoods,
            actual,
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws exception when discarded food standard amount is zero`() {
        val discardedFood = DomainTestData.skimmedMilk.copy(
            standardAmount = 0.0,
        )

        getEquivalentFoods(
            discardedFood = discardedFood,
            discardedFoodAmount = 25.0,
            replacementFoods = DomainTestData.replacementFoods,
        )
    }
}