plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.domain.achievement"
}

dependencies {
    implementation(projects.libs.resources)
    implementation(projects.libs.uikit)

    implementation(libs.akore.blogger)
    implementation(libs.akore.alice.core)
}
