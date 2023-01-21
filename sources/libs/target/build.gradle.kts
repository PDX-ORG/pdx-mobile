plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.target"
}

dependencies {
    implementation(projects.libs.blogger)
    implementation(projects.libs.analytics)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.network)
    implementation(projects.libs.uikit)
    implementation(projects.libs.arc)
}