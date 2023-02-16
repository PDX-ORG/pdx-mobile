plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.ftoggle"
}

dependencies {
    implementation(libs.akore.blogger.core)
    implementation(libs.akore.alice.robo)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.remoteConfig)
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.coroutines.gmsIntegration)
}
