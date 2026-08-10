package barrera.alejandro.swapi.domain.model

object DomainTestData {

    val dairyCategory = Category(
        id = 5,
        name = "Lácteos",
    )

    val gramsUnit = Unit(
        id = 1,
        name = "gr.",
    )

    val millilitersUnit = Unit(
        id = 2,
        name = "ml.",
    )

    val skimmedMilk = Food(
        id = 68,
        name = "Leche desnatada",
        standardAmount = 300.0,
        equivalentAmount = 0.0,
        category = dairyCategory,
        unit = millilitersUnit,
    )

    val skimmedYogurt = Food(
        id = 69,
        name = "Yogur desnatado",
        standardAmount = 300.0,
        equivalentAmount = 0.0,
        category = dairyCategory,
        unit = gramsUnit,
    )

    val greekYogurt = Food(
        id = 70,
        name = "Yogur griego",
        standardAmount = 100.0,
        equivalentAmount = 0.0,
        category = dairyCategory,
        unit = gramsUnit,
    )

    val proteinYogurt = Food(
        id = 71,
        name = "Yogur proteico",
        standardAmount = 200.0,
        equivalentAmount = 0.0,
        category = dairyCategory,
        unit = gramsUnit,
    )

    val replacementFoods = listOf(
        skimmedMilk,
        skimmedYogurt,
        greekYogurt,
        proteinYogurt,
    )

    val equivalentFoods = listOf(
        skimmedYogurt.copy(
            equivalentAmount = 25.0,
        ),
        greekYogurt.copy(
            equivalentAmount = 8.333333333333334,
        ),
        proteinYogurt.copy(
            equivalentAmount = 16.666666666666668,
        ),
    )
}