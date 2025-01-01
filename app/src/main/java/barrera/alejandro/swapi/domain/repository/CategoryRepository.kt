package barrera.alejandro.swapi.domain.repository

import barrera.alejandro.swapi.domain.Result
import barrera.alejandro.swapi.domain.model.Category

interface CategoryRepository {
    suspend fun getAllCategories(): Result<List<Category>>
}