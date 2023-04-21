plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.debugpanel"
}

dependencies {
    implementation(projects.domain.achievement)
    implementation(projects.libs.featureToggle)

    implementation(libs.akore.alice.robo)
}