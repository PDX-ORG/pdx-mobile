@file:OptIn(ExperimentalStdlibApi::class)

package io.github.lexadiky.pdx.lib.fs.statist

import arrow.core.Either
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.serializersModule
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.serializer
import java.net.URI
import kotlin.reflect.KType

class BundleStaticResourceProvider(private val json: Json) : StaticResourceProvider {

    override suspend fun <T : Any> provide(uri: URI, ofType: KType): ResourceDescriptor<T> {
        return ClassLoaderResourceDescriptor(
            classLoader = Thread.currentThread().contextClassLoader,
            resource = createClassLoaderResourcePath(uri),
            ofType = ofType,
            json = json
        )
    }

    private fun createClassLoaderResourcePath(uri: URI): String {
        return uri.toString().replace("${StaticResourceProvider.SCHEME_BUNDLE}://", "")
    }

    @OptIn(ExperimentalSerializationApi::class)
    class ClassLoaderResourceDescriptor<T : Any>(
        private val classLoader: ClassLoader?,
        private val resource: String,
        private val ofType: KType,
        private val json: Json,
    ) : ResourceDescriptor<T> {

        @Suppress("UNCHECKED_CAST")
        override suspend fun read(): Either<ResourceDescriptor.Error, T> = Either.catch {
            require(classLoader != null) { "class loader not available" }

            classLoader.getResourceAsStream(resource).use { input ->

                when (ofType) {
                    String::class -> input.bufferedReader().readText()
                    else -> json.decodeFromStream(serializersModule.serializer(ofType), input)
                } as T
            }
        }.mapLeft { err ->
            BLogger.tag("ClassLoaderResourceDescriptor")
                .error("can't load resource: $resource", err)
            ResourceDescriptor.Error.Unknown(err)
        }
    }
}
