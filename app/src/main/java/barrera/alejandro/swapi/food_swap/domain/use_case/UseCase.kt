package barrera.alejandro.swapi.food_swap.domain.use_case

import barrera.alejandro.swapi.core.domain.Result

interface UseCase<in Params, out Response> {
    operator fun invoke(params: Params): Response
}

interface SuspendUseCase<in Params, out Response> {
    suspend operator fun invoke(params: Params): Result<Response>
}

interface SuspendUseCaseNoParams<out Response> {
    suspend operator fun invoke(): Result<Response>
}