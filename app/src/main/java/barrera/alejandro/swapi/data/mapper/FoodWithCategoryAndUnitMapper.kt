package barrera.alejandro.swapi.data.mapper

import barrera.alejandro.swapi.data.entity.FoodWithCategoryAndUnit
import barrera.alejandro.swapi.domain.model.Food

fun FoodWithCategoryAndUnit.toFood() = Food(
    id = foodEntity.id,
    name = foodEntity.name,
    standardAmount = foodEntity.standardAmount,
    category = categoryEntity.toCategory(),
    unit = unitEntity.toUnit()
)