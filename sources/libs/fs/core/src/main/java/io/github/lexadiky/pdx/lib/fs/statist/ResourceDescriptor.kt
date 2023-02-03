package io.github.lexadiky.pdx.lib.fs.statist

import arrow.core.Either

interface ResourceDescriptor<T> {

    suspend fun read(): Either<Error, T>

    sealed interface Error {

        class Unknown(val reason: Throwable) : Error
    }
}