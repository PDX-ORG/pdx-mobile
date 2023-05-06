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
    implementation(projects.libs.uikit)
    implementation(projects.libs.arc)
    implementation(projects.libs.navigation)
    implementation(projects.libs.analytics.core)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.dynamicBanner)

    implementation(libs.akore.alice.robo)

    debugImplementation(projects.libs.preview)
}
