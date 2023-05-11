@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.compose)
}

android {
    namespace = "io.github.lexadiky.pdx.lib.errorhandler"
}

dependencies {
    api(projects.libs.core)

    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
    implementation(projects.libs.uikit)
    implementation(projects.libs.resources)
}
