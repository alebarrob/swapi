package barrera.alejandro.swapi.food_swap.domain.use_case

import barrera.alejandro.swapi.food_swap.domain.model.Food
import barrera.alejandro.swapi.food_swap.domain.repository.FoodRepository

class GetFoodByCategoryId(
    private val foodRepository: FoodRepository
) : UseCase<@JvmSuppressWildcards GetFoodByCategoryId.Params, @JvmSuppressWildcards List<Food>> {
    override suspend fun invoke(params: Params) = foodRepository.getFoodByCategoryId(
        params.categoryId
    )

    data class Params(
        val categoryId: Int
    )
}