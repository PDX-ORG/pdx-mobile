plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.firebase"
}

dependencies {
    implementation(libs.akore.alice.core)

    implementation(platform(libs.firebase.bom))
    api(libs.firebase.analytics)
    api(libs.firebase.crashlytics)
    api(libs.firebase.remoteConfig)
    api(libs.kotlin.coroutines.gmsIntegration)
}
