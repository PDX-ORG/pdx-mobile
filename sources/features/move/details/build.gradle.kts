plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
    kotlin("plugin.serialization")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.move.details"
}

dependencies {
    implementation(projects.libs.uikit)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.navigation)

    implementation(projects.domain.pokemon)
    implementation(projects.domain.pokemonAsset)

    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
}
