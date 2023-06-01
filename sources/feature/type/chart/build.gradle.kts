@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

dependencies {
    implementation(projects.domain.pokemon)
    implementation(projects.domain.pokemonAsset)

    implementation(projects.library.uikit)
    implementation(projects.library.arc)
    implementation(projects.library.errorHandler)
    implementation(projects.library.nibbler.android)

    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
    implementation(libs.arrow.core)

    debugImplementation(projects.library.preview)
}
