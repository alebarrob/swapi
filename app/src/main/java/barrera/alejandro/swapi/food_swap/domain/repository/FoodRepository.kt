package barrera.alejandro.swapi.food_swap.domain.repository

import barrera.alejandro.swapi.core.domain.Result
import barrera.alejandro.swapi.food_swap.domain.model.Food

interface FoodRepository {
    suspend fun getFoodByCategoryId(categoryId: Int): Result<List<Food>>
    suspend fun getFoodById(id: Int): Result<Food>
}