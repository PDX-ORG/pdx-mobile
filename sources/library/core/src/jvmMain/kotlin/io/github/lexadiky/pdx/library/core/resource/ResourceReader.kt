@file:OptIn(ExperimentalSerializationApi::class)

package io.github.lexadiky.pdx.library.core.resource

import arrow.core.Either
import io.github.lexadiky.pdx.library.core.error.GenericError
import kotlin.reflect.KType
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.serializer

actual class ResourceReader actual constructor(private val json: Json) {

    @Suppress("UNCHECKED_CAST")
    actual suspend fun <T> read(path: String, ofType: KType): Either<GenericError, T> = Either.catch {
        val classLoader = Thread.currentThread().contextClassLoader
        classLoader.getResourceAsStream(path).use { inputStream ->
            json.decodeFromStream(Json.serializersModule.serializer(ofType), inputStream!!)
        } as T
    }.mapLeft { GenericError.invoke("can't load resource", it) }
}