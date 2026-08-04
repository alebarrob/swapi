package barrera.alejandro.swapi.data.mapper

import barrera.alejandro.swapi.data.local.entity.FoodWithCategoryAndUnit
import barrera.alejandro.swapi.domain.model.Food

fun FoodWithCategoryAndUnit.toFood(): Food = Food(
    id = foodEntity.id,
    name = foodEntity.name,
    standardAmount = foodEntity.standardAmount,
    category = categoryEntity.toCategory(),
    unit = unitEntity.toUnit()
)