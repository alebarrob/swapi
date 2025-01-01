package barrera.alejandro.swapi.presentation.mapper

import barrera.alejandro.swapi.domain.model.Unit
import barrera.alejandro.swapi.presentation.model.UnitUi

fun Unit.toUnitUi() = UnitUi(
    id = id,
    name = name
)

fun UnitUi.toUnit() = Unit(
    id = id,
    name = name
)