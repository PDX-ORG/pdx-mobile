@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.compose)
    alias(libs.plugins.pdx.eve)
}

dependencies {
    api(libs.android.navigation.compose)

    api(projects.library.akore.lechuck.robo)

    implementation(libs.akore.alice.robo)
    implementation(projects.library.analytics.core)
    implementation(projects.library.uikit)
    implementation(libs.accompanist.navigation.material)
}
