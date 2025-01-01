package barrera.alejandro.swapi.data.dao

import androidx.room.Dao
import androidx.room.Query
import barrera.alejandro.swapi.data.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<CategoryEntity>
}