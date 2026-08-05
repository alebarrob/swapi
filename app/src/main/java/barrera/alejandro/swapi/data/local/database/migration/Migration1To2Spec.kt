package barrera.alejandro.swapi.data.local.database.migration

import androidx.room.DeleteColumn
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase

@DeleteColumn(tableName = "categories", columnName = "conversion_factor")
class Migration1To2Spec : AutoMigrationSpec {

    override fun onPostMigrate(db: SupportSQLiteDatabase) {
        renameProteinCategory(db)
        insertNewFoods(db)
    }

    private fun renameProteinCategory(db: SupportSQLiteDatabase) {
        db.execSQL(
            sql = UPDATE_CATEGORY_NAME_SQL,
            bindArgs = arrayOf<Any?>(
                PROTEINS_CATEGORY_NAME,
                PROTEINS_CATEGORY_ID.toLong(),
            ),
        )
    }

    private fun insertNewFoods(db: SupportSQLiteDatabase) {
        newFoods.forEach { food ->
            db.execSQL(
                sql = INSERT_FOOD_SQL,
                bindArgs = arrayOf<Any?>(
                    food.name,
                    food.standardAmount,
                    food.categoryId.toLong(),
                    food.unitId.toLong(),
                ),
            )
        }
    }

    private data class FoodSeed(
        val name: String,
        val standardAmount: Double,
        val categoryId: Int,
        val unitId: Int,
    )

    private companion object {

        const val PROTEINS_CATEGORY_ID = 2
        const val FATS_CATEGORY_ID = 3
        const val CARBOHYDRATES_CATEGORY_ID = 4
        const val DAIRY_CATEGORY_ID = 5

        const val GRAMS_UNIT_ID = 1
        const val MILLILITERS_UNIT_ID = 2

        const val PROTEINS_CATEGORY_NAME = "Proteínas"

        const val UPDATE_CATEGORY_NAME_SQL =
            """
            UPDATE categories
            SET name = ?
            WHERE id = ?
            """

        const val INSERT_FOOD_SQL =
            """
            INSERT OR IGNORE INTO food (
                name,
                standard_amount,
                category_id,
                unit_id
            )
            VALUES (?, ?, ?, ?)
            """

        val newFoods = listOf(
            // Carbohydrates
            FoodSeed(
                name = "Ñoquis",
                standardAmount = 60.0,
                categoryId = CARBOHYDRATES_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),
            FoodSeed(
                name = "Crema de arroz",
                standardAmount = 27.0,
                categoryId = CARBOHYDRATES_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),
            FoodSeed(
                name = "Muesli",
                standardAmount = 27.0,
                categoryId = CARBOHYDRATES_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),
            FoodSeed(
                name = "Granola",
                standardAmount = 27.0,
                categoryId = CARBOHYDRATES_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),
            FoodSeed(
                name = "Cuscús",
                standardAmount = 30.0,
                categoryId = CARBOHYDRATES_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),
            FoodSeed(
                name = "Fideos de arroz",
                standardAmount = 30.0,
                categoryId = CARBOHYDRATES_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),

            // Proteins
            FoodSeed(
                name = "Melva",
                standardAmount = 50.0,
                categoryId = PROTEINS_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),
            FoodSeed(
                name = "Caballa",
                standardAmount = 50.0,
                categoryId = PROTEINS_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),
            FoodSeed(
                name = "Lomo asado",
                standardAmount = 95.0,
                categoryId = PROTEINS_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),
            FoodSeed(
                name = "Pollo relleno de La Carloteña",
                standardAmount = 95.0,
                categoryId = PROTEINS_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),

            // Fats
            FoodSeed(
                name = "Crema de cacahuete en polvo",
                standardAmount = 30.0,
                categoryId = FATS_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),

            // Dairy
            FoodSeed(
                name = "Kéfir 0%",
                standardAmount = 300.0,
                categoryId = DAIRY_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),
            FoodSeed(
                name = "Yogur vegetal de soja sin azúcares añadidos",
                standardAmount = 300.0,
                categoryId = DAIRY_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),
            FoodSeed(
                name = "Batido proteico preparado",
                standardAmount = 200.0,
                categoryId = DAIRY_CATEGORY_ID,
                unitId = GRAMS_UNIT_ID,
            ),
            FoodSeed(
                name = "Leche semidesnatada",
                standardAmount = 320.0,
                categoryId = DAIRY_CATEGORY_ID,
                unitId = MILLILITERS_UNIT_ID,
            ),
        )
    }
}