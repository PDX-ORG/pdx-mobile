plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
    kotlin("plugin.serialization")
}

android {
    namespace = "io.github.lexadiky.pdx.domain.pokemon.asset"
}

dependencies {
    implementation(projects.domain.pokemon)
    implementation(projects.libs.network)
    implementation(projects.libs.resources)
    implementation(libs.akore.blogger)
    implementation(libs.arrow.core)
}