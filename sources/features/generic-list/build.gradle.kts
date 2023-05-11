@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.generic.list"
}

dependencies {
    implementation(projects.libs.uikit)
    implementation(projects.libs.network)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.navigation)
    implementation(projects.libs.dynamicBanner)
    implementation(projects.domain.pokemon)
    implementation(projects.domain.achievement)

    implementation(libs.arrow.core)
}
