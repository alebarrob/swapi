package barrera.alejandro.swapi.food_swap.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "units")
data class UnitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)