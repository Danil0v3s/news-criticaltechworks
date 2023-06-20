package br.com.firstsoft.core.common

public sealed class Result<out T, out E> {
    public data class Success<T, E>(val value: T) : Result<T, E>()
    public data class Failure<T, E>(val error: E) : Result<T, E>()
}

public inline fun <T, R, E> Result<T, E>.map(mapper: (T) -> R): Result<R, E> {
    return when (this) {
        is Result.Success -> Result.Success(mapper(value))
        is Result.Failure -> Result.Failure(error)
    }
}

public inline fun <T, E, R> Result<T, E>.mapFailure(mapper: (E) -> R): Result<T, R> {
    return when (this) {
        is Result.Success -> Result.Success(value)
        is Result.Failure -> Result.Failure(mapper(error))
    }
}

public inline fun <T, E> Result<T, E>.onSuccess(onSuccess: (T) -> Unit): Result<T, E> {
    if (this is Result.Success) onSuccess(value)
    return this
}

public inline fun <T, E> Result<T, E>.onFailure(onFailure: (E) -> Unit): Result<T, E> {
    if (this is Result.Failure) onFailure(error)
    return this
}

public inline fun <T, E> Result<T, E>.fold(onSuccess: (T) -> Unit, onFailure: (E) -> Unit) {
    when (this) {
        is Result.Success -> onSuccess(value)
        is Result.Failure -> onFailure(error)
    }
}

@Suppress("NOTHING_TO_INLINE")
public inline fun <T, E> Result<T, E>.getOrNull(): T? = when (this) {
    is Result.Success -> value
    is Result.Failure -> null
}

@Suppress("NOTHING_TO_INLINE")
public inline fun <T, E> Result<T, E>.getOrThrow(): T = when (this) {
    is Result.Success -> value
    is Result.Failure -> throw NullPointerException("Result has an error: $error")
}

@Suppress("NOTHING_TO_INLINE")
public inline fun <T, E> Result<T, E>.errorOrNull(): E? {
    return when (this) {
        is Result.Success -> null
        is Result.Failure -> error
    }
}

public inline val <T, E> Result<T, E>.isSuccess: Boolean
    get() = this is Result.Success

public inline val <T, E> Result<T, E>.isFailure: Boolean
    get() = this is Result.Failure
