@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

dependencies {
    implementation(libs.akore.alice.robo)
    implementation(projects.library.uikit)
    implementation(projects.library.network)
    implementation(projects.library.errorHandler)
    implementation(projects.library.navigation)
    implementation(projects.library.fs.core)
    implementation(projects.library.localeManager)

    implementation(projects.domain.achievement)
    implementation(projects.domain.pokemon)
}
