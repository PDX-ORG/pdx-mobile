@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

dependencies {
    implementation(libs.akore.blogger.core)
    implementation(libs.akore.alice.robo)

    implementation(projects.library.firebase)
    implementation(projects.library.fs.core)
    implementation(projects.library.system)
}
