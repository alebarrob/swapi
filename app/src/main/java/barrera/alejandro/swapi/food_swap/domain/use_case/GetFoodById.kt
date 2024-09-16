package barrera.alejandro.swapi.food_swap.domain.use_case

import barrera.alejandro.swapi.food_swap.domain.model.Food
import barrera.alejandro.swapi.food_swap.domain.repository.FoodRepository

class GetFoodById(
    private val foodRepository: FoodRepository
) : SuspendUseCase<GetFoodById.Params, Food> {
    override suspend fun invoke(params: Params) = foodRepository.getFoodById(params.id)

    data class Params(
        val id: Int
    )
}