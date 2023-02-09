package io.github.lexadiky.pdx.lib.fs.statist

import kotlinx.serialization.json.Json
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
