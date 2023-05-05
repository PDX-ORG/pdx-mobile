plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.lib.preview"
}

dependencies {
    implementation(projects.libs.uikit)
    implementation(projects.libs.target)
    implementation(projects.libs.arc)
    implementation(projects.libs.navigation)

    implementation(libs.akore.alice.robo)

    api(libs.android.compose.ui.preview)
    debugApi(libs.android.compose.ui.tooling)
}