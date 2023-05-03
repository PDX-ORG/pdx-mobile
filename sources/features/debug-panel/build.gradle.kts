plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.debugpanel"
}

dependencies {
    implementation(projects.domain.achievement)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.navigation)
    implementation(projects.libs.uikit)

    implementation(libs.akore.alice.robo)
}
