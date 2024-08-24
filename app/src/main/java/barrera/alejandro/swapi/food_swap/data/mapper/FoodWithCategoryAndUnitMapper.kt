package barrera.alejandro.swapi.food_swap.data.mapper

import barrera.alejandro.swapi.food_swap.data.entity.FoodWithCategoryAndUnit
import barrera.alejandro.swapi.food_swap.domain.model.Food

fun FoodWithCategoryAndUnit.toFood() = Food(
    id = foodEntity.id,
    name = foodEntity.name,
    conversionAmount = foodEntity.conversionAmount,
    category = categoryEntity.toCategory(),
    unit = unitEntity.toUnit()
)