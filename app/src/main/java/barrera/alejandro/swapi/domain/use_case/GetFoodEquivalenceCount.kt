package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.repository.FoodRepository

class GetFoodEquivalenceCount(
    private val foodRepository: FoodRepository
) : FlowUseCaseNoParams<Int> {
    override fun invoke() = foodRepository.getFoodEquivalenceCount()
}