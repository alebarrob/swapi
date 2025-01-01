package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.model.Category
import barrera.alejandro.swapi.domain.repository.CategoryRepository

class GetAllCategories(
    private val categoryRepository: CategoryRepository
) : SuspendUseCaseNoParams<List<Category>> {
    override suspend operator fun invoke() = categoryRepository.getAllCategories()
}