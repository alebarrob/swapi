package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow

class GetFoodEquivalenceCount(private val foodRepository: FoodRepository) {
    operator fun invoke(): Flow<Int> = foodRepository.getFoodEquivalenceCount()
}