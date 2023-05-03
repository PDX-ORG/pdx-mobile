plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.type.details"
}

dependencies {
    implementation(projects.domain.pokemon)
    implementation(projects.domain.pokemonAsset)

    implementation(libs.akore.alice.robo)
    implementation(projects.libs.uikit)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.navigation)

    implementation(libs.akore.blogger.core)
    implementation(libs.arrow.core)
}
