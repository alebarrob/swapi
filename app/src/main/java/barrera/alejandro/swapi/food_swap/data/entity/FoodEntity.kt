package barrera.alejandro.swapi.food_swap.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "food",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category_id"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UnitEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("unit_id"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["category_id"]),
        Index(value = ["unit_id"])
    ]
)
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    @ColumnInfo(name = "standard_amount")
    val standardAmount: Double,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    @ColumnInfo(name = "unit_id")
    val unitId: Int
)
