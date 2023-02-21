plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.debugpanel"
}

dependencies {
    implementation(projects.domain.achievement)

    implementation(projects.libs.uikit)
    implementation(projects.libs.navigation)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.featureToggle)

    implementation(libs.akore.alice.robo)
}