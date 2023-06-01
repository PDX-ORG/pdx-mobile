@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.compose)
}

dependencies {
    implementation(projects.library.uikit)
    implementation(projects.library.target)
    implementation(projects.library.arc)
    implementation(projects.library.nibbler.android)

    implementation(libs.akore.alice.robo)

    api(libs.android.compose.ui.preview)
    debugApi(libs.android.compose.ui.tooling)
}
