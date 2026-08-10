package barrera.alejandro.swapi.domain.repository

import barrera.alejandro.swapi.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getFoodsByCategoryId(categoryId: Int): Flow<List<Food>>
    fun getFoodById(id: Int): Flow<Food>
    fun getFoodEquivalenceCount(): Flow<Int>
    suspend fun incrementFoodEquivalenceCount()
    suspend fun resetFoodEquivalenceCount()
}