@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.compose)
}

dependencies {
    api(libs.android.navigation.compose)
    api(libs.akore.blogger.core)
    api(projects.library.akore.lechuck.core)
    implementation(libs.accompanist.navigation.material)
    implementation(libs.kotlin.coroutines)
}
