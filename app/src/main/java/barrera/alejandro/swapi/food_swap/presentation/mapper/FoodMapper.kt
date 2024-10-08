package barrera.alejandro.swapi.food_swap.presentation.mapper

import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.util.constant.EMPTY_STRING
import barrera.alejandro.swapi.food_swap.domain.model.Food
import barrera.alejandro.swapi.food_swap.presentation.model.FoodUi

private const val DECIMAL_FORMAT = "%.2f"
private const val DECIMAL_REGEX = "[.,]00$"

fun Food.toFoodUi() = FoodUi(
    id = id,
    name = name,
    imageResourceId = getImageResourceId(),
    standardAmount = standardAmount.toString(),
    equivalentAmount = DECIMAL_FORMAT
        .format(equivalentAmount)
        .replace(Regex(DECIMAL_REGEX), EMPTY_STRING),
    categoryUi = category.toCategoryUi(),
    unitUi = unit.toUnitUi()
)

// TODO Create a backend to add the links to the images and delete this map

fun Food.getImageResourceId() = foodNameImageSourceIdMap[name] ?: R.drawable.placeholder_ic

private val foodNameImageSourceIdMap = mapOf(
    "Arándanos" to R.drawable.blueberry_ic,
    "Cerezas" to R.drawable.cherry_ic,
    "Ciruelas" to R.drawable.plum_ic,
    "Dátiles" to R.drawable.date_ic,
    "Frambuesas" to R.drawable.raspberry_ic,
    "Fresas" to R.drawable.strawberry_ic,
    "Higos" to R.drawable.fig_ic,
    "Kiwi" to R.drawable.kiwi_ic,
    "Mandarinas" to R.drawable.tangerine_ic,
    "Mango" to R.drawable.mango_ic,
    "Manzana" to R.drawable.apple_ic,
    "Melocotón" to R.drawable.peach_ic,
    "Melón" to R.drawable.melon_ic,
    "Moras" to R.drawable.blackberry_ic,
    "Naranja" to R.drawable.orange_ic,
    "Nectarina" to R.drawable.nectarine_ic,
    "Nísperos" to R.drawable.loquat_ic,
    "Papaya" to R.drawable.papaya_ic,
    "Pera" to R.drawable.pear_ic,
    "Piña natural" to R.drawable.pineapple_ic,
    "Plátano" to R.drawable.banana_ic,
    "Sandía" to R.drawable.watermelon_ic,
    "Uvas" to R.drawable.grape_ic,
    "Atún al natural" to R.drawable.tuna_ic,
    "Marisco" to R.drawable.seafood_ic,
    "Cerdo (Graso)" to R.drawable.pork_ic,
    "Conejo" to R.drawable.rabbit_ic,
    "Cordero" to R.drawable.lamb_ic,
    "Gluten de trigo" to R.drawable.wheat_gluten_ic,
    "Huevo (Clara)" to R.drawable.egg_white_ic,
    "Huevo entero XL" to R.drawable.egg_ic,
    "Jamón serrano" to R.drawable.spanish_ham_ic,
    "Lomo de cerdo (Magro)" to R.drawable.pork_loin_ic,
    "Lomo embuchado" to R.drawable.stuffed_loin_ic,
    "Pescado blanco" to R.drawable.white_fish_ic,
    "Pollo o pavo (Pechuga sin piel)" to R.drawable.chicken_ic,
    "Proteína en polvo" to R.drawable.protein_powder_ic,
    "Requesón desnatado" to R.drawable.cottage_cheese_ic,
    "Salmón, pescados azules" to R.drawable.salmon_ic,
    "Seitan" to R.drawable.seitan_ic,
    "Ternera, potro (Graso)" to R.drawable.fat_beef_ic,
    "Ternera, potro (Magro)" to R.drawable.lean_beef_ic,
    "Tofu" to R.drawable.tofu_ic,
    "Queso burgos desnatado" to R.drawable.burgos_skimmed_cheese_ic,
    "Queso (Cualquier tipo)" to R.drawable.cheese_ic,
    "Queso fresco batido 0%" to R.drawable.whipped_cheese_ic,
    "Aceite" to R.drawable.oil_ic,
    "Aceitunas sin hueso" to R.drawable.olive_ic,
    "Aguacate" to R.drawable.avocado_ic,
    "Cacao desgrasado en polvo" to R.drawable.cocoa_powder_ic,
    "Coco" to R.drawable.coconut_ic,
    "Chocolate negro" to R.drawable.chocolate_ic,
    "Frutos secos" to R.drawable.nuts_ic,
    "Mantequilla de cacahuete" to R.drawable.peanut_butter_ic,
    "Huevo (Yema)" to R.drawable.egg_yolk_ic,
    "Arroz o pasta (En seco)" to R.drawable.rice_ic,
    "Cereales de desayuno" to R.drawable.cereal_ic,
    "Copos o harina de avena" to R.drawable.oat_ic,
    "Harina de maíz o de arroz" to R.drawable.corn_flour_ic,
    "Legumbres (Crudas)" to R.drawable.legumes_ic,
    "Miel" to R.drawable.honey_ic,
    "Pan" to R.drawable.bread_ic,
    "Pan tostado" to R.drawable.toasted_bread_ic,
    "Patata o boniato (Crudo)" to R.drawable.potato_ic,
    "Puré de patata deshidratado (En polvo)" to R.drawable.potato_powder_ic,
    "Quinoa" to R.drawable.quinoa_ic,
    "Tortitas de arroz o maíz" to R.drawable.rice_cracker_ic,
    "Leche desnatada" to R.drawable.milk_ic,
    "Yogur desnatado" to R.drawable.yogurt_ic,
    "Yogur griego" to R.drawable.greek_yogurt_ic,
    "Yogur proteico" to R.drawable.protein_yogurt_ic
)