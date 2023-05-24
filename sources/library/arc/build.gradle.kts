@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.compose)
}

android {
    namespace = "io.github.lexadiky.pdx.lib.arc"
}


dependencies {
    api(libs.kotlin.coroutines)
    api(libs.android.compose.viewmodel)
}
