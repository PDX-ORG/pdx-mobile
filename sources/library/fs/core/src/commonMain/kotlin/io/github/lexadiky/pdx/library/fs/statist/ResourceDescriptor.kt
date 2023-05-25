package io.github.lexadiky.pdx.library.fs.statist

import arrow.core.Either

interface ResourceDescriptor<T> {

    suspend fun read(): Either<Error, T>

    sealed interface Error {

        val reason: Throwable

        class Unknown(override val reason: Throwable) : Error
    }
}
