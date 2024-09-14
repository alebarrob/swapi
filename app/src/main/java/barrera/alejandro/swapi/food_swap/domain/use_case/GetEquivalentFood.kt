package barrera.alejandro.swapi.food_swap.domain.use_case

import barrera.alejandro.swapi.food_swap.domain.model.Food

/**
 * A use case for getting a list of equivalent food items.
 *
 * This use case takes a list of food items, removes the discarded food, and then calculates
 * the equivalent amount of each remaining food item.
 *
 * @property Params Parameters for this use case, containing the discarded food, its amount, and the replacement food.
 */
class GetEquivalentFood :
    UseCase<@JvmSuppressWildcards GetEquivalentFood.Params, @JvmSuppressWildcards List<Food>> {

    /**
     * Invoke the use case.
     *
     * This function maps each food item in the replacement list to a new food item with its
     * resultAmount adjusted to be equivalent to the discarded food.
     *
     * @param params The parameters for the use case.
     * @return A list of food items with their resultAmount adjusted.
     */
    override fun invoke(params: Params) = params.replacementFood
        .removeDiscardedFood(params.discardedFood)
        .map { food ->
            food.copy(
                resultAmount = getResultAmount(
                    discardedFood = params.discardedFood,
                    discardedFoodAmount = params.discardedFoodAmount,
                    desiredFood = food
                )
            )
        }

    /**
     * Remove the discarded food from the list of replacement foods.
     *
     * @param discardedFood The food to be removed.
     * @return A mutable list of food items without the discarded food.
     */
    private fun List<Food>.removeDiscardedFood(discardedFood: Food) = toMutableList().apply {
        removeIf { food -> food.id == discardedFood.id }
    }

    /**
     * Calculate the equivalent amount of the desired food.
     *
     * @param discardedFood The food that is being replaced.
     * @param discardedFoodAmount The amount of the discarded food.
     * @param desiredFood The food that will replace the discarded food.
     * @return The equivalent amount of the desired food.
     */
    private fun getResultAmount(
        discardedFood: Food,
        discardedFoodAmount: Double,
        desiredFood: Food
    ): Double {
        val standardAmount =
            discardedFoodAmount * discardedFood.category.referenceAmount / discardedFood.conversionAmount

        return standardAmount * desiredFood.conversionAmount / discardedFood.category.referenceAmount
    }

    /**
     * The parameters for the GetEquivalentFood use case.
     *
     * @property discardedFood The food that is being replaced.
     * @property discardedFoodAmount The amount of the discarded food.
     * @property replacementFood The list of food items that will replace the discarded food.
     */
    data class Params(
        val discardedFood: Food,
        val discardedFoodAmount: Double,
        val replacementFood: List<Food>
    )
}