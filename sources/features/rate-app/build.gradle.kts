@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
    alias(libs.plugins.pdx.eve)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.rateapp"
}

dependencies {
    implementation(projects.libs.uikit)
    implementation(projects.libs.microdata)
    implementation(projects.libs.system)
    implementation(projects.libs.arc)
    implementation(projects.libs.analytics.core)

    implementation(libs.akore.alice.robo)
}
