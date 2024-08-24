package barrera.alejandro.swapi.food_swap.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.swapi.food_swap.data.dao.CategoryDao
import barrera.alejandro.swapi.food_swap.data.dao.FoodDao
import barrera.alejandro.swapi.food_swap.data.entity.CategoryEntity
import barrera.alejandro.swapi.food_swap.data.entity.FoodEntity
import barrera.alejandro.swapi.food_swap.data.entity.UnitEntity

@Database(
    entities = [
        CategoryEntity::class,
        UnitEntity::class,
        FoodEntity::class
    ],
    version = 1
)
abstract class SwapiDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun foodDao(): FoodDao
}