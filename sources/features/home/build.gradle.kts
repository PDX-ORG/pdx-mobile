plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
    id("io.github.lexadiky.pdx.plugin.eve")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.home"
}

dependencies {
    implementation(projects.domain.pokemonAsset)
    implementation(projects.domain.pokemon)
    implementation(projects.libs.uikit)
    implementation(projects.libs.navigation)
    implementation(projects.libs.analytics.core)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.dynamicBanner)

    implementation(libs.akore.alice.robo)
}
