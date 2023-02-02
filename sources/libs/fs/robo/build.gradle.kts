plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.fs"
}

dependencies {
    api(projects.libs.fs.core)

    implementation(libs.akore.blogger.core)
    implementation(libs.akore.alice.robo)
}