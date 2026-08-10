package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.repository.FoodRepository

class ResetFoodEquivalenceCount(private val foodRepository: FoodRepository) {
    suspend operator fun invoke() {
        foodRepository.resetFoodEquivalenceCount()
    }
}