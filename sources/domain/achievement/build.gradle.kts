plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.domain.achievement"
}

dependencies {
    implementation(projects.libs.alice)
    implementation(projects.libs.resources)
    implementation(libs.akore.blogger)
    implementation(projects.libs.uikit)
}