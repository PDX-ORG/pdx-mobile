@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

dependencies {
    api(projects.library.fs.core)

    implementation(projects.library.network)
    implementation(projects.library.firebase)

    implementation(libs.akore.blogger.core)
    implementation(libs.akore.alice.robo)
    implementation(libs.arrow.core)
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.serialization.json)
}
