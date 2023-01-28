plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.drawer"
}

dependencies {
    implementation(projects.libs.aliceRobo)
    implementation(projects.libs.uikit)
    implementation(projects.libs.navigation)
    implementation(projects.libs.featureToggle)
}