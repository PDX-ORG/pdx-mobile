plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.fs"
}

dependencies {
    implementation(libs.akore.blogger)
    implementation(projects.libs.aliceRobo)
}