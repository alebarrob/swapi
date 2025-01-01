package barrera.alejandro.swapi.data.mapper

import barrera.alejandro.swapi.data.entity.UnitEntity
import barrera.alejandro.swapi.domain.model.Unit

fun UnitEntity.toUnit() = Unit(id = id, name = name)