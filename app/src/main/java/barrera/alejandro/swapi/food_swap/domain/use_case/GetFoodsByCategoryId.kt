package barrera.alejandro.swapi.food_swap.domain.use_case

import barrera.alejandro.swapi.food_swap.domain.model.Food
import barrera.alejandro.swapi.food_swap.domain.repository.FoodRepository

class GetFoodsByCategoryId(
    private val foodRepository: FoodRepository
) : SuspendUseCase<GetFoodsByCategoryId.Params, List<Food>> {
    override suspend fun invoke(params: Params) = foodRepository.getFoodsByCategoryId(
        params.categoryId
    )

    data class Params(
        val categoryId: Int
    )
}