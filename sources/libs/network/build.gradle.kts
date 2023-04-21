plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.jvm")
}

dependencies {
    api(libs.ktor.core)

    implementation(libs.akore.alice.core)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.json)
    implementation(libs.ktor.contentNegotiation)
}
