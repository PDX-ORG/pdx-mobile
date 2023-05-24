@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.debugpanel"
}

dependencies {
    implementation(projects.domain.achievement)
    implementation(projects.library.featureToggle)
    implementation(projects.library.navigation)
    implementation(projects.library.uikit)

    implementation(libs.akore.alice.robo)
}
