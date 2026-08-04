package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.Result
import barrera.alejandro.swapi.domain.model.Food
import barrera.alejandro.swapi.domain.repository.FoodRepository

class GetFoodsByCategoryId(private val foodRepository: FoodRepository) {
    suspend operator fun invoke(categoryId: Int): Result<List<Food>> =
        foodRepository.getFoodsByCategoryId(categoryId)
}