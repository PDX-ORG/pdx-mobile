@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.jvm)
}

dependencies {
    api(libs.ktor.core)

    implementation(libs.akore.alice.core)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.json)
    implementation(libs.ktor.contentNegotiation)
}
