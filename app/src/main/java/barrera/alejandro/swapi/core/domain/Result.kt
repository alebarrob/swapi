package barrera.alejandro.swapi.core.domain

import kotlin.coroutines.cancellation.CancellationException

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val exception: Throwable) : Result<Nothing>()

    inline fun <R> fold(success: (T) -> R, failure: (Throwable?) -> R): R {
        return when (this) {
            is Success -> success(value)
            is Failure -> failure(exception)
        }
    }

    companion object {
        suspend fun <T> from(call: suspend () -> T): Result<T> {
            return try {
                Success(call())
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Failure(e)
            }
        }
    }
}
