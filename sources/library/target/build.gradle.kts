@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

dependencies {
    implementation(projects.library.analytics.core)
    implementation(projects.library.analytics.firebaseAndroid)
    implementation(projects.library.featureToggle)
    implementation(projects.library.network)
    implementation(projects.library.uikit)
    implementation(projects.library.fs.robo)
    implementation(projects.library.firebase)
    implementation(projects.library.system)
    implementation(projects.library.microdata)

    implementation(projects.domain.pokemon)

    implementation(libs.android.mops.datastore.android)

    implementation(libs.akore.blogger.core)
    implementation(libs.akore.blogger.logcat)
    implementation(libs.akore.alice.robo)

    implementation(libs.kotlin.coroutines.gmsIntegration)
}
