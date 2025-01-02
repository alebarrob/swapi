package barrera.alejandro.swapi.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.swapi.data.local.dao.CategoryDao
import barrera.alejandro.swapi.data.local.dao.FoodDao
import barrera.alejandro.swapi.data.local.entity.CategoryEntity
import barrera.alejandro.swapi.data.local.entity.FoodEntity
import barrera.alejandro.swapi.data.local.entity.UnitEntity

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