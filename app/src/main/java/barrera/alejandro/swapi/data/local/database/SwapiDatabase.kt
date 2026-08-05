package barrera.alejandro.swapi.data.local.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import barrera.alejandro.swapi.data.local.dao.CategoryDao
import barrera.alejandro.swapi.data.local.dao.FoodDao
import barrera.alejandro.swapi.data.local.database.migration.Migration1To2Spec
import barrera.alejandro.swapi.data.local.entity.CategoryEntity
import barrera.alejandro.swapi.data.local.entity.FoodEntity
import barrera.alejandro.swapi.data.local.entity.UnitEntity

@Database(
    entities = [
        CategoryEntity::class,
        UnitEntity::class,
        FoodEntity::class,
    ],
    version = SwapiDatabase.DATABASE_VERSION,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = Migration1To2Spec::class),
    ],
)
abstract class SwapiDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun foodDao(): FoodDao

    companion object {
        const val DATABASE_NAME = "swapi_database"
        const val FILE_PATH = "database/swapi_database.db"
        const val DATABASE_VERSION = 2
    }
}