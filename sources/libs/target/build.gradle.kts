plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.target"
}

dependencies {
    implementation(projects.libs.analytics)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.network)
    implementation(projects.libs.uikit)
    implementation(projects.libs.fs.robo)
    implementation(projects.libs.firebase)
    implementation(projects.libs.system)

    implementation(libs.akore.blogger.core)
    implementation(libs.akore.blogger.logcat)
    implementation(libs.akore.alice.robo)

    implementation(libs.kotlin.coroutines.gmsIntegration)
}
