package barrera.alejandro.swapi.food_swap.data.dao

import androidx.room.Dao
import androidx.room.Query
import barrera.alejandro.swapi.food_swap.data.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    suspend fun getAll(): List<CategoryEntity>
}