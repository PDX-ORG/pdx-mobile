@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.compose)
}

dependencies {
    implementation(projects.library.buildConfig)

    api(projects.library.resources)
    api(projects.library.microdata)
    api(projects.library.core)

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
