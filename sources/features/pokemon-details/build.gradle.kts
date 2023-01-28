plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.pokemon.details"
}

dependencies {
    implementation(libs.akore.alice.robo)
    implementation(projects.libs.uikit)
    implementation(projects.libs.network)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.navigation)
    implementation(libs.akore.blogger)

    implementation(projects.domain.pokemon)
    implementation(projects.domain.achievement)
}