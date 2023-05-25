@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.news"
}

dependencies {
    implementation(projects.library.uikit)
    implementation(projects.library.arc)
    implementation(projects.library.errorHandler)
    implementation(projects.library.featureToggle)
    implementation(projects.library.network)
    implementation(projects.library.navigation)

    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
    implementation(libs.arrow.core)
    implementation(libs.kotlin.serialization.json)
}
