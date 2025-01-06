package barrera.alejandro.swapi.domain.use_case

import barrera.alejandro.swapi.domain.Result
import kotlinx.coroutines.flow.Flow

interface UseCase<Params, Response> {
    operator fun invoke(params: Params): Response
}

interface SuspendUseCase<Params, Response> {
    suspend operator fun invoke(params: Params): Result<Response>
}

interface SuspendUseCaseNoParams<Response> {
    suspend operator fun invoke(): Result<Response>
}

interface SuspendUseCaseNoParamsNoResponse {
    suspend operator fun invoke()
}

interface FlowUseCaseNoParams<Response> {
    operator fun invoke(): Flow<Response>
}