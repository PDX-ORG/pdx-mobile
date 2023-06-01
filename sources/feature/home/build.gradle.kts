@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
    alias(libs.plugins.pdx.eve)
}

dependencies {
    implementation(projects.domain.pokemonAsset)
    implementation(projects.domain.pokemon)
    implementation(projects.library.uikit)
    implementation(projects.library.arc)
    implementation(projects.library.nibbler.android)
    implementation(projects.library.analytics.core)
    implementation(projects.library.errorHandler)
    implementation(projects.library.dynamicBanner)

    implementation(libs.akore.alice.robo)

    debugImplementation(projects.library.preview)
}
