package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.util.extension.normalizeDecimalSeparator

class IsValidFoodAmount {

    operator fun invoke(amount: String): Boolean {
        if (!amount.matches(FOOD_AMOUNT_REGEX)) {
            return false
        }

        val numericAmount = amount
            .normalizeDecimalSeparator()
            .toDoubleOrNull()
            ?: return false

        return numericAmount > 0 && numericAmount.isFinite()
    }

    private companion object {
        val FOOD_AMOUNT_REGEX = Regex("""\d+([.,]\d+)?""")
    }
}