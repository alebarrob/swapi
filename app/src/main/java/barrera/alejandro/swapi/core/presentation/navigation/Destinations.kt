package barrera.alejandro.swapi.core.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object Category

@Serializable
data class FoodSelection(val categoryId: Int)

@Serializable
data class FoodAmountSelection(val foodId: Int)

@Serializable
data class FoodResult(val foodId: Int, val amount: String)