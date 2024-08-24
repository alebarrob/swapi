package barrera.alejandro.swapi.food_swap.presentation.mapper

import barrera.alejandro.swapi.food_swap.domain.model.Unit
import barrera.alejandro.swapi.food_swap.presentation.model.UnitUi

fun Unit.toUnitUi() = UnitUi(
    id = id,
    name = name
)