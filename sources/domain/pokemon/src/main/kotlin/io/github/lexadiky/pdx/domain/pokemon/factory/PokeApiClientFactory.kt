package io.github.lexadiky.pdx.domain.pokemon.factory

import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.info
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.network.CacheSettings
import io.lexadiky.pokeapi.util.PokeApiClientLogger
import java.io.File
import kotlin.time.Duration.Companion.seconds

internal class PokeApiClientFactory {

    fun create(): PokeApiClient = PokeApiClient {
        cache = CacheSettings.FileStorage(createCacheFile())
        timeout = TIMEOUT_SECONDS.seconds
        logger = Logger()
    }

    private fun createCacheFile(): File =
        File(System.getProperty(TEMPORARY_BASE_DIR_PROPERTY), TEMPORARY_DIRECTORY)

    class Logger : PokeApiClientLogger {

        override fun onNetworkReceive(method: String, statusCode: Int, url: String) =
            BLogger.tag("PokeApi").info("RECEIVE: $method/$statusCode <= $url")

        override fun onNetworkSend(method: String, url: String) =
            BLogger.tag("PokeApi").info("SEND: $method => $url")
    }

    companion object {

        private const val TEMPORARY_BASE_DIR_PROPERTY = "java.io.tmpdir"
        private const val TEMPORARY_DIRECTORY = "pokeapi"
        private const val TIMEOUT_SECONDS = 30
    }
}
