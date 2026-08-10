package barrera.alejandro.swapi.data.mapper

import barrera.alejandro.swapi.data.local.entity.UnitEntity
import barrera.alejandro.swapi.domain.model.Unit

fun UnitEntity.toUnit(): Unit = Unit(id = id, name = name)