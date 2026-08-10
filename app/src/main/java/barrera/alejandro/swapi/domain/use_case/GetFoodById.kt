package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.model.Food
import barrera.alejandro.swapi.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow

class GetFoodById(private val foodRepository: FoodRepository) {
    operator fun invoke(id: Int): Flow<Food> = foodRepository.getFoodById(id)
}