plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.akore.disco.android"
}

dependencies {
    implementation(libs.koin.compose)

    api(libs.akore.alice.core)
}