@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

dependencies {
    implementation(projects.libs.resources)
    implementation(projects.libs.uikit)

    implementation(libs.akore.blogger.core)
    implementation(libs.akore.alice.core)
}
