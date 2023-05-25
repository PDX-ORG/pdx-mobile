@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.move.details"
}

dependencies {
    implementation(projects.library.uikit)
    implementation(projects.library.errorHandler)
    implementation(projects.library.navigation)

    implementation(projects.domain.pokemon)
    implementation(projects.domain.pokemonAsset)

    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
}
