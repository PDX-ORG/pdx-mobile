@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

android {
    namespace = "io.github.lexadiky.pdx.lib.target"
}

dependencies {
    implementation(projects.libs.analytics.core)
    implementation(projects.libs.analytics.firebaseAndroid)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.network)
    implementation(projects.libs.uikit)
    implementation(projects.libs.fs.robo)
    implementation(projects.libs.firebase)
    implementation(projects.libs.system)
    implementation(projects.libs.microdata)

    implementation(projects.domain.pokemon)

    implementation(libs.android.mops.datastore.android)

    implementation(libs.akore.blogger.core)
    implementation(libs.akore.blogger.logcat)
    implementation(libs.akore.alice.robo)

    implementation(libs.kotlin.coroutines.gmsIntegration)
}
