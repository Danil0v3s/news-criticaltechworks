package br.com.firstsoft.core.network

import br.com.firstsoft.core.common.Result
import com.slack.eithernet.ApiResult

fun <T : Any> ApiResult<T, Throwable>.toResult(): Result<T, Throwable> {
    return when (this) {
        is ApiResult.Failure -> Result.Failure(error())
        is ApiResult.Success -> Result.Success(value)
    }
}

fun <E : Throwable> ApiResult.Failure<E>.error(): Throwable {
    return when (this) {
        is ApiResult.Failure.ApiFailure -> error ?: Throwable("Api Failure")
        is ApiResult.Failure.HttpFailure -> error ?: Throwable("HTTP Failure")
        is ApiResult.Failure.NetworkFailure -> error
        is ApiResult.Failure.UnknownFailure -> error
    }
}
