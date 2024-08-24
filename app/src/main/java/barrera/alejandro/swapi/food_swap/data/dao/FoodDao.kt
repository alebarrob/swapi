package barrera.alejandro.swapi.food_swap.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import barrera.alejandro.swapi.food_swap.data.entity.FoodWithCategoryAndUnit

@Dao
interface FoodDao {
    @Transaction
    @Query("SELECT * FROM food WHERE category_id == :categoryId")
    suspend fun getFoodByCategoryId(categoryId: Int): List<FoodWithCategoryAndUnit>
}