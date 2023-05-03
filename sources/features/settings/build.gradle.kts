plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.settings"
}

dependencies {
    implementation(libs.akore.alice.robo)
    implementation(projects.libs.uikit)
    implementation(projects.libs.network)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.navigation)
    implementation(projects.libs.fs.core)
    implementation(projects.libs.localeManager)

    implementation(projects.domain.achievement)
    implementation(projects.domain.pokemon)
}
