plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.uikit"
}

dependencies {
    implementation(projects.libs.buildConfig)

    api(projects.libs.resources)
    api(projects.libs.fs.core)

    api(libs.android.core)
    api(libs.android.material)
    api(libs.android.lifecycle.runtime)
    api(libs.android.activity.compose)
    api(libs.android.compose.ui.core)
    api(libs.android.compose.ui.graphics)
    api(libs.android.compose.ui.preview)
    api(libs.android.compose.material3)

    api(libs.accompanist.flowlayout)
    api(libs.accompanist.pager)

    api(libs.coil.compose.base)

    implementation(libs.mpchart)

    implementation(libs.akore.blogger.core)
    implementation(libs.akore.alice.robo)
}
