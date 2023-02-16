package io.github.lexadiky.pdx.lib.fs.statist

import java.net.URI
import kotlin.reflect.KType
import kotlin.reflect.typeOf

interface StaticResourceProvider {

    suspend fun <T : Any> provide(uri: URI, ofType: KType): ResourceDescriptor<T>

    companion object {

        const val SCHEME_BUNDLE = "bundle"
        const val SCHEME_CONFIG = "remote-config"
    }
}

suspend inline fun <reified T : Any> StaticResourceProvider.provide(uri: String): ResourceDescriptor<T> {
    return provide(URI.create(uri), typeOf<T>())
}
