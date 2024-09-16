package barrera.alejandro.swapi.food_swap.domain.use_case

class IsFoodAmountValid : UseCase<IsFoodAmountValid.Params, Boolean> {
    override fun invoke(params: Params) = params.amount.matches(Regex("\\d+(\\.\\d+)?"))

    data class Params(
        val amount: String
    )
}