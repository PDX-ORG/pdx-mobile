package io.github.lexadiky.pdx.lib.fs.statist

import arrow.core.Either
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.pdx.lib.firebase.FirebaseRemoteConfigProvider
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.serializersModule
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.serializer
import java.net.URI
import kotlin.reflect.KType

class RemoteConfigResourceProvider(
    private val firebaseRemoteConfig: FirebaseRemoteConfigProvider,
    private val json: Json,
) : StaticResourceProvider {

    override suspend fun <T : Any> provide(uri: URI, ofType: KType): ResourceDescriptor<T> {
        return FirebaseRemoteConfigResourceDescriptor(
            uri = uri,
            firebaseRemoteConfig = firebaseRemoteConfig.get(),
            ofType = ofType,
            json = json
        )
    }

    class FirebaseRemoteConfigResourceDescriptor<T : Any>(
        private val uri: URI,
        private val ofType: KType,
        private val json: Json,
        private val firebaseRemoteConfig: FirebaseRemoteConfig,
    ) : ResourceDescriptor<T> {

        @Suppress("UNCHECKED_CAST")
        override suspend fun read(): Either<ResourceDescriptor.Error, T> = Either.catch {
            require(uri.host == "firebase") { "only firebase remote configs are supported" }

            val id = uri.path.drop(1) // drop slash at the beginning
            val input = firebaseRemoteConfig.getString(id)
            json.decodeFromString(serializersModule.serializer(ofType), input) as T
        }.mapLeft { err ->
            BLogger.tag("ClassLoaderResourceDescriptor")
                .error("can't load config: $uri, with", err)
            ResourceDescriptor.Error.Unknown(err)
        }
    }
}
