@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.target)
}

android {
    defaultConfig {
        applicationId = "io.github.lexadiky.pdx.typecalc"
    }
}

dependencies {
    implementation(projects.library.target)
    implementation(projects.library.uikit)
    implementation(libs.akore.alice.robo)
    implementation(projects.library.featureToggle)
    implementation(projects.library.network)
    implementation(projects.library.nibbler.android)

    implementation(projects.library.system)
    implementation(libs.akore.blogger.core)
    implementation(projects.domain.achievement)
    implementation(projects.feature.settings)
    implementation(projects.feature.type.chart)
}
