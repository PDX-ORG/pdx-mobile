plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.domain.achievement"
}

dependencies {
    implementation(projects.libs.arc)
    implementation(projects.libs.resources)
    implementation(projects.libs.blogger)
    implementation(projects.libs.uikit)
}