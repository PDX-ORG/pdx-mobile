@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.compose)
    alias(libs.plugins.pdx.eve)
}


dependencies {
    implementation(projects.library.uikit)
    implementation(projects.library.analytics.core)

    api(libs.android.navigation.compose)
    implementation(libs.accompanist.navigation.material)
    implementation(libs.kotlin.coroutines)
    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
}
