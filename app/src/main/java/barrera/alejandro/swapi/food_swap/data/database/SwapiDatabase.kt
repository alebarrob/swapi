package barrera.alejandro.swapi.food_swap.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.swapi.food_swap.data.dao.CategoryDao
import barrera.alejandro.swapi.food_swap.data.entity.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 1)
abstract class SwapiDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}