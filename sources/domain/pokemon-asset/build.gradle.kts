@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.android)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.domain.pokemon)
    implementation(projects.library.resources)
    implementation(projects.library.nibbler.android)

    implementation(libs.arrow.core)
}
