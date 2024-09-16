package barrera.alejandro.swapi.food_swap.presentation.mapper

import barrera.alejandro.swapi.food_swap.domain.model.Category
import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi

fun Category.toCategoryUi() = CategoryUi(
    id = id,
    name = name.uppercase(),
    conversionFactor = conversionFactor
)