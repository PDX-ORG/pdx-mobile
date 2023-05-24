package io.github.lexadiky.pdx.lib.fs.statist

import kotlinx.serialization.json.Json
import java.net.URI
import kotlin.reflect.KType

class RoboStaticResourceProvider(
    private val bundleStaticResourceProvider: BundleStaticResourceProvider,
    private val remoteConfigResourceProvider: RemoteConfigResourceProvider
) : StaticResourceProvider {

    override suspend fun <T : Any> provide(uri: URI, ofType: KType): ResourceDescriptor<T> {
        return when (uri.scheme) {
            StaticResourceProvider.SCHEME_BUNDLE -> bundleStaticResourceProvider.provide(uri, ofType)
            StaticResourceProvider.SCHEME_CONFIG -> remoteConfigResourceProvider.provide(uri, ofType)
            else -> throw NotImplementedError("unsupported schema: ${uri.scheme}")
        }
    }
}
