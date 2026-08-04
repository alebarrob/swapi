package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.Result
import barrera.alejandro.swapi.domain.model.Food
import barrera.alejandro.swapi.domain.repository.FoodRepository

class GetFoodById(private val foodRepository: FoodRepository) {
    suspend operator fun invoke(id: Int): Result<Food> = foodRepository.getFoodById(id)
}