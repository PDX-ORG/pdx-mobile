@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

dependencies {
    implementation(libs.akore.alice.core)

    implementation(platform(libs.firebase.bom))
    api(libs.firebase.analytics)
    api(libs.firebase.crashlytics)
    api(libs.firebase.remoteConfig)
    api(libs.firebase.performance)
    api(libs.kotlin.coroutines.gmsIntegration)
}
