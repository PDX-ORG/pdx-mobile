plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.fs"
}

dependencies {
    implementation(projects.libs.blogger)
    implementation(projects.libs.arc)
}