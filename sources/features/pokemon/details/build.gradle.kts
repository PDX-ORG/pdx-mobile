@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.pokemon.details"
}

dependencies {
    implementation(libs.akore.alice.robo)
    implementation(projects.libs.uikit)
    implementation(projects.libs.network)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.navigation)
    implementation(libs.akore.blogger.core)
    implementation(libs.arrow.core)

    implementation(projects.domain.pokemon)
    implementation(projects.domain.pokemonAsset)
    implementation(projects.domain.achievement)
}
