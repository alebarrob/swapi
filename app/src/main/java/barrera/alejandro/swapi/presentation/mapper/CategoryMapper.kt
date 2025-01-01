package barrera.alejandro.swapi.presentation.mapper

import barrera.alejandro.swapi.domain.model.Category
import barrera.alejandro.swapi.presentation.model.CategoryUi

fun Category.toCategoryUi() = CategoryUi(
    id = id,
    name = name.uppercase(),
    conversionFactor = conversionFactor
)