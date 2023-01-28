package io.github.lexadiky.pdx.lib.network

import io.github.lexadiky.akore.alice.module
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import okhttp3.Cache
import java.io.File

val NetworkModule by module("network") {
    single {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }
    single {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(inject())
            }
            engine {
                config {
                    // TODO enabled cache
                    /*
                    cache(
                        Cache(
                            directory = File("", "http_cache"),
                            maxSize = 50L * 1024L * 1024L // 50 MiB
                        )
                    )
                     */
                }
            }
        }
    }
}