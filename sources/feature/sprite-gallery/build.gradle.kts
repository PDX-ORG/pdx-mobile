@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

dependencies {
    implementation(projects.domain.pokemonAsset)
    implementation(projects.domain.pokemon)

    implementation(projects.library.uikit)
    implementation(projects.library.nibbler.android)
    implementation(projects.library.errorHandler)

    implementation(libs.akore.alice.robo)
}
