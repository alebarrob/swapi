package barrera.alejandro.swapi.food_swap.domain.model

data class Food(
    val id: Int,
    val name: String,
    val standardAmount: Double,
    val equivalentAmount: Double = 0.0,
    val category: Category,
    val unit: Unit
)