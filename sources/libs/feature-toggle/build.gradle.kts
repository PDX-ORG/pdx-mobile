plugins {
    alias(libs.plugins.pdx.convention.library.android)
}

android {
    namespace = "io.github.lexadiky.pdx.lib.ftoggle"
}

dependencies {
    implementation(libs.akore.blogger.core)
    implementation(libs.akore.alice.robo)

    implementation(projects.libs.firebase)
    implementation(projects.libs.fs.core)
    implementation(projects.libs.system)
}
