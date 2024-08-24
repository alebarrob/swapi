package barrera.alejandro.swapi.food_swap.data.mapper

import barrera.alejandro.swapi.food_swap.data.entity.UnitEntity
import barrera.alejandro.swapi.food_swap.domain.model.Unit

fun UnitEntity.toUnit() = Unit(id = id, name = name)