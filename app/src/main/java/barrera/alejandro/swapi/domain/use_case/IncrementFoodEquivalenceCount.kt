package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.repository.FoodRepository

class IncrementFoodEquivalenceCount(private val foodRepository: FoodRepository) {
    suspend fun invoke() {
        foodRepository.incrementFoodEquivalenceCount()
    }
}