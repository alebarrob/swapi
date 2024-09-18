package barrera.alejandro.swapi.food_swap.presentation.model

import androidx.annotation.DrawableRes
import barrera.alejandro.swapi.core.util.constant.EMPTY_STRING


data class FoodUi(
    val id: Int,
    val name: String,
    @DrawableRes val imageResourceId: Int,
    val standardAmount: String,
    val equivalentAmount: String = EMPTY_STRING,
    val categoryUi: CategoryUi,
    val unitUi: UnitUi
)