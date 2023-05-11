@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.ability.details"
}

dependencies {
    implementation(projects.domain.pokemon)
    implementation(projects.libs.uikit)
    implementation(projects.libs.errorHandler)

    implementation(libs.akore.alice.robo)
    implementation(libs.arrow.core)
}
