package barrera.alejandro.swapi.food_swap.domain.use_case

import barrera.alejandro.swapi.food_swap.domain.model.Category
import barrera.alejandro.swapi.food_swap.domain.repository.CategoryRepository

class GetAllCategories(
    private val categoryRepository: CategoryRepository
) : UseCaseNoParams<@JvmSuppressWildcards List<Category>> {
    override suspend operator fun invoke() = categoryRepository.getAllCategories()
}