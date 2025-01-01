package barrera.alejandro.swapi.domain.repository

import barrera.alejandro.swapi.domain.Result
import barrera.alejandro.swapi.domain.model.Food

interface FoodRepository {
    suspend fun getFoodsByCategoryId(categoryId: Int): Result<List<Food>>
    suspend fun getFoodById(id: Int): Result<Food>
}