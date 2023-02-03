package io.github.lexadiky.pdx.lib.fs.statist

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.serializersModule
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.serializer
import java.net.URI
import kotlin.reflect.KType

class RoboStaticResourceProvider(
    private val json: Json
) : StaticResourceProvider {

    private val bundleStaticResourceProvider = BundleStaticResourceProvider(json)

    override suspend fun <T : Any> provide(uri: URI, ofType: KType): ResourceDescriptor<T> {
        return when (uri.scheme) {
            StaticResourceProvider.SCHEME_BUNDLE -> bundleStaticResourceProvider.provide(uri, ofType)
            else -> throw NotImplementedError("unsupported schema: ${uri.scheme}")
        }
    }
}
