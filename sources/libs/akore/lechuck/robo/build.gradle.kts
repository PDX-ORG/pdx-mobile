plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
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
