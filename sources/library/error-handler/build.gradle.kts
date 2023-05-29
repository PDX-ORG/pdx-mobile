@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.compose)
}

dependencies {
    api(projects.library.core)

    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
    implementation(projects.library.uikit)
    implementation(projects.library.resources)
}
