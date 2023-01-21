plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
    id("io.github.lexadiky.pdx.plugin.eve")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.navigation"
}

dependencies {
    api(libs.android.navigation.compose)
    implementation(projects.libs.arc)
    implementation(projects.libs.analytics)
    implementation(projects.libs.uikit)
}