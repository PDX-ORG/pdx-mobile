@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.github.lexadiky.pdx.domain.pokemon.asset"
}

dependencies {
    implementation(projects.domain.pokemon)
    implementation(projects.libs.resources)
    implementation(projects.libs.navigation)

    implementation(libs.arrow.core)
}
