@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
    alias(libs.plugins.pdx.eve)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.home"
}

dependencies {
    implementation(projects.domain.pokemonAsset)
    implementation(projects.domain.pokemon)
    implementation(projects.library.uikit)
    implementation(projects.library.arc)
    implementation(projects.library.navigation)
    implementation(projects.library.analytics.core)
    implementation(projects.library.errorHandler)
    implementation(projects.library.dynamicBanner)

    implementation(libs.akore.alice.robo)

    debugImplementation(projects.library.preview)
}
