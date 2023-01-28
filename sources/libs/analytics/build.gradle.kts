plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.analytics"
}

dependencies {
    implementation(libs.akore.blogger)
    implementation(libs.akore.alice.robo)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}