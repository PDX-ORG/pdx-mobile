plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.ftoggle"
}

dependencies {
    implementation(projects.libs.blogger)
    implementation(projects.libs.arc)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.remoteConfig)
}