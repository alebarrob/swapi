package barrera.alejandro.swapi.food_swap.domain.repository

import barrera.alejandro.swapi.core.domain.Result
import barrera.alejandro.swapi.food_swap.domain.model.Category

interface CategoryRepository {
    suspend fun getAllCategories(): Result<List<Category>>
}