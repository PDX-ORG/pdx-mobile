plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.lib.uikit"
}

dependencies {
    implementation(projects.libs.buildConfig)

    api(projects.libs.resources)
    api(projects.libs.microdata)

    api(libs.android.core)
    api(libs.android.material)
    api(libs.android.lifecycle.runtime)
    api(libs.android.activity.compose)

    implementation(platform(libs.android.compose.bom))
    api(libs.android.compose.ui.core)
    api(libs.android.compose.ui.graphics)
    api(libs.android.compose.material3)

    api(libs.accompanist.flowlayout)
    implementation(libs.accompanist.placeholder)

    api(libs.coil.compose.base)

    implementation(libs.akore.blogger.core)
    implementation(libs.akore.alice.robo)
}
