plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

android {
    namespace = "io.github.lexadiky.pdx.lib.analytics.firebase"
}

dependencies {
    implementation(projects.libs.firebase)
    implementation(projects.libs.analytics.core)

    implementation(libs.akore.blogger.core)
}
