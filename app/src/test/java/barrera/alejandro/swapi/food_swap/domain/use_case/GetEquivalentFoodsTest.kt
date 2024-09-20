package barrera.alejandro.swapi.food_swap.domain.use_case

import barrera.alejandro.swapi.food_swap.domain.model.Category
import barrera.alejandro.swapi.food_swap.domain.model.Food
import barrera.alejandro.swapi.food_swap.domain.model.Unit
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class GetEquivalentFoodsTest {

    private lateinit var getEquivalentFoods: GetEquivalentFoods

    @Before
    fun setUp() {
        getEquivalentFoods = GetEquivalentFoods()
    }

    @Test
    fun `test getEquivalentFoods returns equivalent foods`() {
        val expected = listOf(
            Food(
                id = 69,
                name = "Yogur desnatado",
                standardAmount = 300.0,
                equivalentAmount = 25.0,
                category = Category(
                    id = 5,
                    name = "Lácteos",
                    conversionFactor = 100.0
                ),
                unit = Unit(
                    id = 1,
                    name = "gr."
                )
            ),
            Food(
                id = 70,
                name = "Yogur griego",
                standardAmount = 100.0,
                equivalentAmount = 8.333333333333334,
                category = Category(
                    id = 5,
                    name = "Lácteos",
                    conversionFactor = 100.0
                ),
                unit = Unit(
                    id = 1,
                    name = "gr."
                )
            ),
            Food(
                id = 71,
                name = "Yogur proteico",
                standardAmount = 200.0,
                equivalentAmount = 16.666666666666668,
                category = Category(
                    id = 5,
                    name = "Lácteos",
                    conversionFactor = 100.0
                ),
                unit = Unit(
                    id = 1,
                    name = "gr."
                )
            )
        )
        val actual = getEquivalentFoods.invoke(
            GetEquivalentFoods.Params(
                discardedFood = Food(
                    id = 68,
                    name = "Leche desnatada",
                    standardAmount = 300.0,
                    equivalentAmount = 0.0,
                    category = Category(
                        id = 5,
                        name = "Lácteos",
                        conversionFactor = 100.0
                    ),
                    unit = Unit(
                        id = 2,
                        name = "ml."
                    )
                ),
                discardedFoodAmount = 25.0,
                replacementFoods = arrayListOf(
                    Food(
                        id = 68,
                        name = "Leche desnatada",
                        standardAmount = 300.0,
                        equivalentAmount = 0.0,
                        category = Category(
                            id = 5,
                            name = "Lácteos",
                            conversionFactor = 100.0
                        ),
                        unit = Unit(
                            id = 2,
                            name = "ml."
                        )
                    ),
                    Food(
                        id = 69,
                        name = "Yogur desnatado",
                        standardAmount = 300.0,
                        equivalentAmount = 0.0,
                        category = Category(
                            id = 5,
                            name = "Lácteos",
                            conversionFactor = 100.0
                        ),
                        unit = Unit(
                            id = 1,
                            name = "gr."
                        )
                    ),
                    Food(
                        id = 70,
                        name = "Yogur griego",
                        standardAmount = 100.0,
                        equivalentAmount = 0.0,
                        category = Category(
                            id = 5,
                            name = "Lácteos",
                            conversionFactor = 100.0
                        ),
                        unit = Unit(
                            id = 1,
                            name = "gr."
                        )
                    ),
                    Food(
                        id = 71,
                        name = "Yogur proteico",
                        standardAmount = 200.0,
                        equivalentAmount = 0.0,
                        category = Category(
                            id = 5,
                            name = "Lácteos",
                            conversionFactor = 100.0
                        ),
                        unit = Unit(
                            id = 1,
                            name = "gr."
                        )
                    )
                )
            )
        )

        assertEquals(expected, actual)
    }
}