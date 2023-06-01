@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

dependencies {
    implementation(libs.akore.alice.robo)

    implementation(projects.library.uikit)
    implementation(projects.library.featureToggle)
    implementation(projects.library.nibbler.android)
}
