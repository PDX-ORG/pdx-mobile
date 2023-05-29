@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

dependencies {
    implementation(projects.library.firebase)
    implementation(projects.library.analytics.core)

    implementation(libs.akore.blogger.core)
}
