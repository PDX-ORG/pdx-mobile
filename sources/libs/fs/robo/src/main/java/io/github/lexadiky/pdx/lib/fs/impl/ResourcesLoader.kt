package io.github.lexadiky.pdx.lib.fs.impl

import arrow.core.Either
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class ResourcesLoader(private val json: Json) {

    suspend inline fun <reified T> load(path: String): Either<Error, T> = withContext(Dispatchers.IO) {
        Either.catch {
            val body = Thread.currentThread().contextClassLoader!!
                .getResourceAsStream(path)
                .bufferedReader()
                .readText()
            json.decodeFromString<T>(body)
        }.mapLeft { throwable ->
            BLogger.tag("ResourceLoader").error("can't load resource body", throwable)
            Error
        }
    }

    object Error
}
