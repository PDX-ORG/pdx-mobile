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
    implementation(projects.libs.aliceRobo)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.network)
    implementation(projects.libs.navigation)
    implementation(projects.libs.analytics)
    implementation(libs.akore.blogger)
    implementation(projects.domain.achievement)
    implementation(projects.features.settings)
    implementation(projects.features.typeChart)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
}