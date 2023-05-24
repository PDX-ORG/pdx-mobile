@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

dependencies {
    implementation(projects.library.resources)
    implementation(projects.library.uikit)

    implementation(libs.akore.blogger.core)
    implementation(libs.akore.alice.core)
}
