@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
    alias(libs.plugins.pdx.eve)
}

dependencies {
    implementation(projects.library.uikit)
    implementation(projects.library.microdata)
    implementation(projects.library.system)
    implementation(projects.library.arc)
    implementation(projects.library.analytics.core)

    implementation(libs.akore.alice.robo)
}
