package barrera.alejandro.swapi.food_swap.domain.use_case

import barrera.alejandro.swapi.food_swap.domain.model.Food

/**
 * A use case for calculating the equivalent amounts of replacement food items.
 *
 * This use case follows these steps:
 *
 * 1. Removes the discarded food from the list of food items.
 *
 * 2. For each remaining food item in the list, it calculates the amount that would be equivalent
 *    to the discarded food. This calculation involves a two-step conversion:
 *
 *    a. The amount of discarded food is first converted to a "standard" amount. This is done by
 *       multiplying the amount of discarded food by the conversion factor of its category and then
 *       dividing by the standard amount of the discarded food. The result is an amount in a reference
 *       or "standard" unit. For example, for the 'fruit' category, the standard unit is 'apples'.
 *
 *    b. The "standard" amount is then converted to the amount of the desired food. This is done by
 *       multiplying the "standard" amount by the standard amount of the desired food and then
 *       dividing by the conversion factor of the discarded food's category. The result is the
 *       equivalent amount of the desired food.
 *
 * This process ensures that the amounts of the food items are comparable by converting them to a
 * common "standard" unit before performing the calculation.
 *
 * @property Params Parameters for this use case, containing the discarded food, its amount, and the replacement foods.
 */
class GetEquivalentFoods : UseCase<GetEquivalentFoods.Params, List<Food>> {

    override fun invoke(params: Params) = params.replacementFoods
        .removeDiscardedFood(params.discardedFood)
        .map { food ->
            food.copy(
                equivalentAmount = getEquivalentAmount(
                    discardedFood = params.discardedFood,
                    discardedFoodAmount = params.discardedFoodAmount,
                    desiredFood = food
                )
            )
        }

    private fun List<Food>.removeDiscardedFood(discardedFood: Food) = toMutableList().apply {
        removeIf { food -> food.id == discardedFood.id }
    }

    private fun getEquivalentAmount(
        discardedFood: Food,
        discardedFoodAmount: Double,
        desiredFood: Food
    ): Double {
        val discardedToStandardAmount =
            discardedFoodAmount * discardedFood.category.conversionFactor / discardedFood.standardAmount

        val equivalentDesiredFoodAmount =
            discardedToStandardAmount * desiredFood.standardAmount / discardedFood.category.conversionFactor

        return equivalentDesiredFoodAmount
    }

    data class Params(
        val discardedFood: Food,
        val discardedFoodAmount: Double,
        val replacementFoods: List<Food>
    )
}