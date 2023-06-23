package io.github.lexadiky.pdx.library.core.resource

import arrow.core.Either
import io.github.lexadiky.pdx.library.core.error.GenericError
import kotlin.reflect.KType
import kotlin.reflect.typeOf
import kotlinx.serialization.json.Json

expect class ResourceReader(json: Json) {

    suspend fun <T> read(path: String, ofType: KType): Either<GenericError, T>
}

suspend inline fun <reified T> ResourceReader.read(path: String): Either<GenericError, T> {
    return read(path, typeOf<T>())
}
