@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.typechart"
}

dependencies {
    implementation(projects.domain.pokemon)
    implementation(projects.domain.pokemonAsset)

    implementation(projects.libs.uikit)
    implementation(projects.libs.arc)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.navigation)

    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
    implementation(libs.arrow.core)

    debugImplementation(projects.libs.preview)
}
