package barrera.alejandro.swapi.data.mapper

import barrera.alejandro.swapi.data.local.entity.CategoryEntity
import barrera.alejandro.swapi.domain.model.Category

fun CategoryEntity.toCategory(): Category = Category(
    id = id,
    name = name,
)