plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.target"
}

dependencies {
    implementation(libs.akore.blogger.core)
    implementation(libs.akore.blogger.logcat)
    implementation(libs.akore.alice.robo)

    implementation(projects.libs.analytics)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.network)
    implementation(projects.libs.uikit)
    implementation(projects.libs.fs.robo)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
}
