package barrera.alejandro.swapi.food_swap.presentation.mapper

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import barrera.alejandro.swapi.food_swap.domain.model.Category
import barrera.alejandro.swapi.food_swap.presentation.model.CategoryUi

fun Category.toCategoryUi() = CategoryUi(
    id = id,
    name = name.uppercase(),
    referenceAmount = referenceAmount
)

fun CategoryUi.toCategory() = Category(
    id = id,
    name = name.lowercase().capitalize(Locale.current),
    referenceAmount = referenceAmount
)