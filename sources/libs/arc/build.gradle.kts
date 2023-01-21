plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.arc"
}

dependencies {
    api(libs.kotlin.coroutines)
    api(libs.arrow.core)

    implementation(libs.kotlin.reflection)
    implementation(libs.koin.compose)
    implementation(libs.android.compose.ui.core)
    implementation(libs.android.compose.viewmodel)
}