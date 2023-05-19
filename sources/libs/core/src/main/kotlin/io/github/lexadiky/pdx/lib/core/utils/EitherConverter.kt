package io.github.lexadiky.pdx.lib.core.utils

import arrow.core.Either
import io.github.lexadiky.pdx.lib.core.lce.Lce

/**
 * Converts [Result] to [Either].
 * Failed result is converted to [Either.Left]<[Throwable]>.
 */
fun <T> Result<T>.asEither(): Either<Throwable, T> = if (isSuccess) {
    Either.Right(getOrThrow())
} else {
    Either.Left(exceptionOrNull()!!)
}

/**
 * Converts [Either] to [Lce].
 */
fun <E, T> Either<E, T>.asLce(): Lce<E, T> = when (this) {
    is Either.Left -> Lce.Error(value)
    is Either.Right -> Lce.Content(value)
}
