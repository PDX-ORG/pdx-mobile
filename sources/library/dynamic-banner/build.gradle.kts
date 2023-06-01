@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.compose)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.library.uikit)
    implementation(projects.library.network)
    implementation(projects.library.nibbler.android)
    implementation(projects.library.resources)
    implementation(projects.library.fs.core)
    implementation(projects.library.core)
    implementation(projects.library.errorHandler)

    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
    implementation(libs.kotlin.serialization.json)
}
