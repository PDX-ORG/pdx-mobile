plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.toolbar"
}

dependencies {
    implementation(libs.akore.alice.robo)
    implementation(projects.libs.uikit)
    implementation(projects.libs.navigation)
}
