@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.spritegallery"
}

dependencies {
    implementation(projects.domain.pokemonAsset)
    implementation(projects.domain.pokemon)

    implementation(projects.library.uikit)
    implementation(projects.library.navigation)
    implementation(projects.library.errorHandler)

    implementation(libs.akore.alice.robo)
}
