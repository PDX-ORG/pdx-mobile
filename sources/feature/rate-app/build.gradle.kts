@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
    alias(libs.plugins.pdx.eve)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.rateapp"
}

dependencies {
    implementation(projects.library.uikit)
    implementation(projects.library.microdata)
    implementation(projects.library.system)
    implementation(projects.library.arc)
    implementation(projects.library.analytics.core)

    implementation(libs.akore.alice.robo)
}
