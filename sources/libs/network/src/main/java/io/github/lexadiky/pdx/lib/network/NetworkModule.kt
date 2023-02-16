package io.github.lexadiky.pdx.lib.network

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

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
        }
    }
}
