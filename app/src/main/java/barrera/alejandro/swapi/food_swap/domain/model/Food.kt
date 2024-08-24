package barrera.alejandro.swapi.food_swap.domain.model

data class Food(
    val id: Int,
    val name: String,
    val conversionAmount: Double,
    val category: Category,
    val unit: Unit
)