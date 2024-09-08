package barrera.alejandro.swapi.food_swap.presentation.model

import androidx.annotation.DrawableRes

data class FoodUi(
    val id: Int,
    val name: String,
    @DrawableRes val imageResourceId: Int,
    val conversionAmount: String,
    val resultAmount: String = "",
    val categoryUi: CategoryUi,
    val unitUi: UnitUi
)