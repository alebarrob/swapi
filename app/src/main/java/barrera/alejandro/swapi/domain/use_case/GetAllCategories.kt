package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.model.Category
import barrera.alejandro.swapi.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategories(private val categoryRepository: CategoryRepository) {
    operator fun invoke(): Flow<List<Category>> = categoryRepository.getAllCategories()
}