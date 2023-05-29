@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

dependencies {
    implementation(projects.domain.pokemon)
    implementation(projects.domain.pokemonAsset)

    implementation(libs.akore.alice.robo)
    implementation(projects.library.uikit)
    implementation(projects.library.errorHandler)
    implementation(projects.library.navigation)

    implementation(libs.akore.blogger.core)
    implementation(libs.arrow.core)
}
