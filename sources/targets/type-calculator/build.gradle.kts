plugins {
    id("io.github.lexadiky.pdx.plugin.module.target")
}

android {
    namespace = "io.github.lexadiky.pdx"

    defaultConfig {
        applicationId = "io.github.lexadiky.pdx.typecalc"
    }
}

dependencies {
    implementation(projects.libs.target)
    implementation(projects.libs.uikit)
    implementation(libs.akore.alice.robo)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.network)
    implementation(projects.libs.navigation)
    implementation(libs.akore.blogger.core)
    implementation(projects.domain.achievement)
    implementation(projects.features.settings)
    implementation(projects.features.typeChart)
}
