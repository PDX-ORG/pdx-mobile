package io.github.lexadiky.pdx.lib.core.utils

import arrow.core.Either
import io.github.lexadiky.pdx.lib.core.lce.Lce

fun <T> Result<T>.asEither(): Either<Throwable, T> = if (isSuccess) {
    Either.Right(getOrThrow())
} else {
    Either.Left(exceptionOrNull()!!)
}

fun <E, T> Result<T>.asLce(onError: (Throwable) -> E): Lce<E, T> = if (isSuccess) {
    Lce.Content(getOrThrow())
} else {
    Lce.Error(onError(exceptionOrNull()!!))
}


fun <E, T> Either<E, T>.asLce(): Lce<E, T> = when (this) {
    is Either.Left -> Lce.Error(value)
    is Either.Right -> Lce.Content(value)
}
