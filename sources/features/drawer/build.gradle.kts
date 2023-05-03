plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.drawer"
}

dependencies {
    implementation(libs.akore.alice.robo)

    implementation(projects.libs.uikit)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.navigation)
}
