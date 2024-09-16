package barrera.alejandro.swapi.food_swap.data.mapper

import barrera.alejandro.swapi.food_swap.data.entity.CategoryEntity
import barrera.alejandro.swapi.food_swap.domain.model.Category

fun CategoryEntity.toCategory() = Category(
    id = id,
    name = name,
    conversionFactor = conversionFactor
)