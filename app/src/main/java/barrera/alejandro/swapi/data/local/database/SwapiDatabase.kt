package barrera.alejandro.swapi.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.swapi.data.local.dao.CategoryDao
import barrera.alejandro.swapi.data.local.dao.FoodDao
import barrera.alejandro.swapi.data.local.database.SwapiDatabase.Companion.DATABASE_VERSION
import barrera.alejandro.swapi.data.local.entity.CategoryEntity
import barrera.alejandro.swapi.data.local.entity.FoodEntity
import barrera.alejandro.swapi.data.local.entity.UnitEntity

@Database(
    entities = [
        CategoryEntity::class,
        UnitEntity::class,
        FoodEntity::class
    ],
    version = DATABASE_VERSION
)
abstract class SwapiDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun foodDao(): FoodDao

    companion object {
        const val DATABASE_NAME = "swapi_database"
        const val FILE_PATH = "database/swapi_database.db"
        const val DATABASE_VERSION = 1
    }
}