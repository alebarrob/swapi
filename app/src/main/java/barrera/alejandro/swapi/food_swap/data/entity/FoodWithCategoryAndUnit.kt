package barrera.alejandro.swapi.food_swap.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FoodWithCategoryAndUnit(
    @Embedded val foodEntity: FoodEntity,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val categoryEntity: CategoryEntity,

    @Relation(
        parentColumn = "unit_id",
        entityColumn = "id"
    )
    val unitEntity: UnitEntity
)