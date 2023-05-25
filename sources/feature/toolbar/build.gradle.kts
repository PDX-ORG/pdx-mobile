@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.toolbar"
}

dependencies {
    implementation(libs.akore.alice.robo)
    implementation(projects.library.uikit)
    implementation(projects.library.navigation)
}
