plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.network"
}

dependencies {
    api(projects.libs.aliceRobo)
    api(libs.kotlin.serialization.json)
    api(libs.ktor.core)
    api(libs.ktor.okhttp)
    api(libs.ktor.json)
    api(libs.ktor.contentNegotiation)
}