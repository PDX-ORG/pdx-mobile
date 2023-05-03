plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

android {
    namespace = "io.github.lexadiky.pdx.domain.achievement"
}

dependencies {
    implementation(projects.libs.resources)
    implementation(projects.libs.uikit)

    implementation(libs.akore.blogger.core)
    implementation(libs.akore.alice.core)
}
