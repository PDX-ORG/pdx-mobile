package io.github.lexadiky.pdx.library.errorhandler

import arrow.core.Either
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.pdx.library.core.error.ErrorType
import kotlin.reflect.KClass

internal fun <E, T> Either<E, T>.classify(tag: String): Either<UIError, T> where E : ErrorType.Any, E : Throwable {
    if (this is Either.Left) {
        BLogger.tag(tag)
            .error(this.value.message ?: "no error message", this.value)
    }
    return mapLeft { type ->
        when (type) {
            is ErrorType.Generic -> UIError.generic()
            is ErrorType.Network -> UIError.network()
            else -> UIError.generic()
        }
    }
}

fun <E, T> Either<E, T>.classify(by: KClass<*>): Either<UIError, T> where E : ErrorType.Any, E : Throwable =
    classify(by.toString())

