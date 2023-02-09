plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.jvm")
}

dependencies {
    api(libs.akore.alice.core)
    api(libs.kotlin.serialization.json)
    api(libs.ktor.core)
    api(libs.ktor.okhttp)
    api(libs.ktor.json)
    api(libs.ktor.contentNegotiation)
}
