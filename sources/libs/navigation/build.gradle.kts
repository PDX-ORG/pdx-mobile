plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
    id("io.github.lexadiky.pdx.plugin.eve")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.navigation"
}

dependencies {
    api(libs.android.navigation.compose)

    api(libs.akore.lechuck.robo)

    implementation(libs.akore.alice.robo)
    implementation(projects.libs.analytics)
    implementation(projects.libs.uikit)
    implementation(libs.accompanist.navigation.material)
}
