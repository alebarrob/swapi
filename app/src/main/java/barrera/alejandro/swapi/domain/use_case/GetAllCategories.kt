package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.model.Category
import barrera.alejandro.swapi.domain.repository.CategoryRepository
import barrera.alejandro.swapi.domain.Result

class GetAllCategories(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): Result<List<Category>> = categoryRepository.getAllCategories()
}