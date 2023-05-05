plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.lib.arc"
}


dependencies {
    api(libs.kotlin.coroutines)
    api(libs.android.compose.viewmodel)
}
