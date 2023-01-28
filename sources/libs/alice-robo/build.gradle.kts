plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.akore.disco.android"
}

dependencies {
    api(projects.libs.alice)
    implementation(libs.koin.compose)
}