@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

android {
    namespace = "io.github.lexadiky.pdx.lib.system"
}

dependencies {
    implementation(libs.akore.alice.robo)
    implementation(libs.android.play.review)
    implementation(libs.kotlin.coroutines.gmsIntegration)
}
