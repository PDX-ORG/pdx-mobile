@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

dependencies {
    implementation(projects.domain.achievement)
    implementation(projects.library.featureToggle)
    implementation(projects.library.nibbler.android)
    implementation(projects.library.uikit)

    implementation(libs.akore.alice.robo)
}
