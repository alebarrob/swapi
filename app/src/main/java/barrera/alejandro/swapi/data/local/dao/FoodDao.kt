package barrera.alejandro.swapi.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import barrera.alejandro.swapi.data.local.entity.FoodWithCategoryAndUnit
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Transaction
    @Query("SELECT * FROM food WHERE category_id == :categoryId")
    fun getFoodsByCategoryId(categoryId: Int): Flow<List<FoodWithCategoryAndUnit>>

    @Transaction
    @Query("SELECT * FROM food WHERE id == :id")
    fun getFoodById(id: Int): Flow<FoodWithCategoryAndUnit>
}