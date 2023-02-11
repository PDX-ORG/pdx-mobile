plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.widget.launcher"
}

dependencies {
    implementation(libs.android.compose.glance)
}
