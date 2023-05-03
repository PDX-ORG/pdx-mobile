plugins {
    alias(libs.plugins.pdx.convention.feature)
    alias(libs.plugins.pdx.eve)
}

android {
    namespace = "io.github.lexadiky.pdx.lib.navigation"
}

dependencies {
    api(libs.android.navigation.compose)

    api(projects.libs.akore.lechuck.robo)

    implementation(libs.akore.alice.robo)
    implementation(projects.libs.analytics.core)
    implementation(projects.libs.uikit)
    implementation(libs.accompanist.navigation.material)
}
