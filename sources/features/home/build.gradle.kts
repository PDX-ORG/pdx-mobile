plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.home"
}

dependencies {
    implementation(projects.domain.pokemonAsset)
    implementation(projects.domain.pokemon)

    implementation(projects.libs.uikit)
    implementation(projects.libs.navigation)
    implementation(projects.libs.errorHandler)

    implementation(libs.akore.alice.robo)
}
