package io.github.lexadiky.pdx.library.fs.statist

import java.net.URI
import kotlin.reflect.KType

class RoboStaticResourceProvider(
    private val remoteConfigResourceProvider: RemoteConfigResourceProvider
) : StaticResourceProvider {

    override suspend fun <T : Any> provide(uri: URI, ofType: KType): ResourceDescriptor<T> {
        return when (uri.scheme) {
            StaticResourceProvider.SCHEME_CONFIG -> remoteConfigResourceProvider.provide(uri, ofType)
            else -> throw NotImplementedError("unsupported schema: ${uri.scheme}")
        }
    }
}
