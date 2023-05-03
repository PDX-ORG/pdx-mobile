plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.lib.navigation.lc"
}

dependencies {
    api(libs.android.navigation.compose)
    api(libs.akore.blogger.core)
    api(projects.libs.akore.lechuck.core)
    implementation(libs.accompanist.navigation.material)
    implementation(libs.kotlin.coroutines)
}
