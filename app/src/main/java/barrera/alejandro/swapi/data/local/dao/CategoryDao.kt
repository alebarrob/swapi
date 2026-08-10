package barrera.alejandro.swapi.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import barrera.alejandro.swapi.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<CategoryEntity>>
}