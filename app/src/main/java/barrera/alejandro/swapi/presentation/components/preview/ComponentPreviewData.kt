package barrera.alejandro.swapi.presentation.components.preview

import androidx.annotation.DrawableRes
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.presentation.model.CategoryUi
import barrera.alejandro.swapi.presentation.model.FoodUi
import barrera.alejandro.swapi.presentation.model.UnitUi

internal object ComponentPreviewData {

    private val fruitCategory = CategoryUi(
        id = 1,
        name = "Frutas",
    )

    val gramsUnit = UnitUi(
        id = 1,
        name = "gr.",
    )

    val blueberryFood = createFood(
        id = 1,
        name = "Arándanos",
        imageResourceId = R.drawable.blueberry_ic,
        standardAmount = "120",
    )

    val foods = listOf(
        blueberryFood,
        createFood(
            id = 2,
            name = "Cerezas",
            imageResourceId = R.drawable.cherry_ic,
            standardAmount = "145",
        ),
        createFood(
            id = 3,
            name = "Ciruelas",
            imageResourceId = R.drawable.plum_ic,
            standardAmount = "145",
        ),
        createFood(
            id = 4,
            name = "Dátiles",
            imageResourceId = R.drawable.date_ic,
            standardAmount = "20",
        ),
        createFood(
            id = 5,
            name = "Frambuesas",
            imageResourceId = R.drawable.raspberry_ic,
            standardAmount = "200",
        ),
        createFood(
            id = 6,
            name = "Fresas",
            imageResourceId = R.drawable.strawberry_ic,
            standardAmount = "250",
        ),
        createFood(
            id = 7,
            name = "Higos",
            imageResourceId = R.drawable.fig_ic,
            standardAmount = "160",
        ),
        createFood(
            id = 8,
            name = "Kiwi",
            imageResourceId = R.drawable.kiwi_ic,
            standardAmount = "140",
        ),
        createFood(
            id = 9,
            name = "Mandarinas",
            imageResourceId = R.drawable.tangerine_ic,
            standardAmount = "170",
        ),
    )

    val foodsWithResult = listOf(
        createFood(
            id = 1,
            name = "Arándanos",
            imageResourceId = R.drawable.blueberry_ic,
            standardAmount = "120",
            equivalentAmount = "15",
        ),
        createFood(
            id = 2,
            name = "Cerezas",
            imageResourceId = R.drawable.cherry_ic,
            standardAmount = "145",
            equivalentAmount = "18",
        ),
        createFood(
            id = 3,
            name = "Ciruelas",
            imageResourceId = R.drawable.plum_ic,
            standardAmount = "145",
            equivalentAmount = "18",
        ),
        createFood(
            id = 4,
            name = "Dátiles",
            imageResourceId = R.drawable.date_ic,
            standardAmount = "20",
            equivalentAmount = "2.5",
        ),
        createFood(
            id = 5,
            name = "Frambuesas",
            imageResourceId = R.drawable.raspberry_ic,
            standardAmount = "200",
            equivalentAmount = "25",
        ),
        createFood(
            id = 6,
            name = "Fresas",
            imageResourceId = R.drawable.strawberry_ic,
            standardAmount = "250",
            equivalentAmount = "31",
        ),
        createFood(
            id = 7,
            name = "Higos",
            imageResourceId = R.drawable.fig_ic,
            standardAmount = "160",
            equivalentAmount = "20",
        ),
        createFood(
            id = 8,
            name = "Kiwi",
            imageResourceId = R.drawable.kiwi_ic,
            standardAmount = "140",
            equivalentAmount = "18",
        ),
        createFood(
            id = 9,
            name = "Mandarinas",
            imageResourceId = R.drawable.tangerine_ic,
            standardAmount = "170",
            equivalentAmount = "21",
        ),
    )

    private fun createFood(
        id: Int,
        name: String,
        @DrawableRes imageResourceId: Int,
        standardAmount: String,
        equivalentAmount: String = "",
    ) = FoodUi(
        id = id,
        name = name,
        imageResourceId = imageResourceId,
        standardAmount = standardAmount,
        equivalentAmount = equivalentAmount,
        categoryUi = fruitCategory,
        unitUi = gramsUnit,
    )
}