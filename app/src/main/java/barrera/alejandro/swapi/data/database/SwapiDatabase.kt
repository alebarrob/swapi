package barrera.alejandro.swapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.swapi.data.dao.CategoryDao
import barrera.alejandro.swapi.data.dao.FoodDao
import barrera.alejandro.swapi.data.entity.CategoryEntity
import barrera.alejandro.swapi.data.entity.FoodEntity
import barrera.alejandro.swapi.data.entity.UnitEntity

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