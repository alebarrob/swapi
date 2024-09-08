package barrera.alejandro.swapi.food_swap.domain.use_case

import barrera.alejandro.swapi.core.domain.Result
import barrera.alejandro.swapi.food_swap.domain.model.Food
import barrera.alejandro.swapi.food_swap.domain.repository.FoodRepository

class GetEquivalentFood(
    private val foodRepository: FoodRepository
) : UseCase<@JvmSuppressWildcards GetEquivalentFood.Params, @JvmSuppressWildcards List<Food>> {
    override suspend fun invoke(params: Params): Result<List<Food>> {
        val foodToConvert = foodRepository.getFoodByCategoryId(params.food.category.id)
        val convertedFood = foodToConvert.mapSuccess { food ->
            food.map { desiredFood ->
                desiredFood.copy(
                    resultAmount = convertFood(
                        food = params.food,
                        foodAmount = params.foodAmount,
                        desiredFood = desiredFood
                    )
                )
            }.toMutableList().apply {
                removeIf { it.id == params.food.id }
            }
        }

        return convertedFood
    }

    private fun convertFood(
        food: Food,
        foodAmount: Double,
        desiredFood: Food,
    ): Double {
        val standardAmount = foodAmount * food.category.referenceAmount / food.conversionAmount

        return standardAmount * desiredFood.conversionAmount / food.category.referenceAmount
    }

    data class Params(
        val food: Food,
        val foodAmount: Double
    )
}