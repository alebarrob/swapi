package barrera.alejandro.swapi.presentation.model

import barrera.alejandro.swapi.R

object PresentationTestData {

    val fruitCategory = CategoryUi(
        id = 1,
        name = "FRUTAS",
    )

    val gramsUnit = UnitUi(
        id = 1,
        name = "gr.",
    )

    val blueberry = FoodUi(
        id = 1,
        name = "Arándanos",
        imageResourceId = R.drawable.blueberry_ic,
        standardAmount = "120",
        equivalentAmount = "25",
        categoryUi = fruitCategory,
        unitUi = gramsUnit,
    )

    val cherry = FoodUi(
        id = 2,
        name = "Cerezas",
        imageResourceId = R.drawable.cherry_ic,
        standardAmount = "145",
        equivalentAmount = "30.21",
        categoryUi = fruitCategory,
        unitUi = gramsUnit,
    )
}