plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.settings"
}

dependencies {
    implementation(projects.libs.arc)
    implementation(projects.libs.uikit)
    implementation(projects.libs.network)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.navigation)

    implementation(projects.domain.achievement)
}