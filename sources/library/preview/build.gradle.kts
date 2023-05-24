@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.compose)
}

android {
    namespace = "io.github.lexadiky.pdx.lib.preview"
}

dependencies {
    implementation(projects.library.uikit)
    implementation(projects.library.target)
    implementation(projects.library.arc)
    implementation(projects.library.navigation)

    implementation(libs.akore.alice.robo)

    api(libs.android.compose.ui.preview)
    debugApi(libs.android.compose.ui.tooling)
}
