plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
    kotlin("plugin.serialization")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.news"
}

dependencies {
    implementation(projects.libs.uikit)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.network)
    implementation(projects.libs.navigation)

    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
    implementation(libs.arrow.core)
    implementation(libs.kotlin.serialization.json)
}
