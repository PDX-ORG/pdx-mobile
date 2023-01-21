plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.resources"
}

dependencies {
    api(libs.android.compose.ui.core)
    api(libs.kotlin.datetime)
}