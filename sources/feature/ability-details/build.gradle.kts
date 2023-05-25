@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.ability.details"
}

dependencies {
    implementation(projects.domain.pokemon)
    implementation(projects.library.uikit)
    implementation(projects.library.errorHandler)

    implementation(libs.akore.alice.robo)
    implementation(libs.arrow.core)
}
