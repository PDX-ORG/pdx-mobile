plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.toolbar"
}

dependencies {
    implementation(projects.libs.aliceRobo)
    implementation(projects.libs.uikit)
    implementation(projects.libs.navigation)
}