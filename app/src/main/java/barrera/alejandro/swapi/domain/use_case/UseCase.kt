package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.Result

interface UseCase<Params, Response> {
    operator fun invoke(params: Params): Response
}

interface SuspendUseCase<Params, Response> {
    suspend operator fun invoke(params: Params): Result<Response>
}

interface SuspendUseCaseNoParams<Response> {
    suspend operator fun invoke(): Result<Response>
}