package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.model.Food

/**
 * Calculates equivalent amounts for alternative foods in the same category.
 *
 * The discarded food is removed from the result. Each remaining food receives
 * an equivalent amount based on the ratio between its standard amount and the
 * standard amount of the discarded food:
 *
 * equivalent amount =
 * discarded amount × desired standard amount ÷ discarded standard amount
 */
class GetEquivalentFoods {

    operator fun invoke(
        discardedFood: Food,
        discardedFoodAmount: Double,
        replacementFoods: List<Food>,
    ): List<Food> {
        require(discardedFood.standardAmount > 0) {
            INVALID_STANDARD_AMOUNT_MESSAGE
        }

        return replacementFoods
            .filterNot { food ->
                food.id == discardedFood.id
            }
            .map { desiredFood ->
                desiredFood.copy(
                    equivalentAmount = calculateEquivalentAmount(
                        discardedFood = discardedFood,
                        discardedFoodAmount = discardedFoodAmount,
                        desiredFood = desiredFood,
                    ),
                )
            }
    }

    private fun calculateEquivalentAmount(
        discardedFood: Food,
        discardedFoodAmount: Double,
        desiredFood: Food,
    ): Double = discardedFoodAmount * desiredFood.standardAmount / discardedFood.standardAmount

    private companion object {
        const val INVALID_STANDARD_AMOUNT_MESSAGE =
            "Discarded food standard amount must be greater than zero"
    }
}