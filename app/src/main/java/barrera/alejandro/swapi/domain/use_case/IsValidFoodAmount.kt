package barrera.alejandro.swapi.domain.use_case

class IsValidFoodAmount {
    fun invoke(amount: String): Boolean = amount.matches(FOOD_AMOUNT_REGEX)

    private companion object {
        val FOOD_AMOUNT_REGEX = Regex("""\d+([.,]\d+)?""")
    }
}