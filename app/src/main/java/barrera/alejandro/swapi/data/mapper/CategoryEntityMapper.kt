package barrera.alejandro.swapi.data.mapper

import barrera.alejandro.swapi.data.entity.CategoryEntity
import barrera.alejandro.swapi.domain.model.Category

fun CategoryEntity.toCategory() = Category(
    id = id,
    name = name,
    conversionFactor = conversionFactor
)