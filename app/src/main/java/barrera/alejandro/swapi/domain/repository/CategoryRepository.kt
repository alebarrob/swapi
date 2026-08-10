package barrera.alejandro.swapi.domain.repository

import barrera.alejandro.swapi.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<Category>>
}