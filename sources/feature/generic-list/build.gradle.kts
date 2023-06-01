@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

dependencies {
    implementation(projects.library.uikit)
    implementation(projects.library.network)
    implementation(projects.library.errorHandler)
    implementation(projects.library.nibbler.android)
    implementation(projects.library.dynamicBanner)
    implementation(projects.domain.pokemon)
    implementation(projects.domain.achievement)

    implementation(libs.arrow.core)
}
