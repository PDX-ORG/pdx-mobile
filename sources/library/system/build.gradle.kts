@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

dependencies {
    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
    implementation(libs.android.play.review)
    implementation(libs.kotlin.coroutines.gmsIntegration)
}
