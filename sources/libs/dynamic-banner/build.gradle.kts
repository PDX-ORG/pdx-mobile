plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
    kotlin("plugin.serialization")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.dynbanner"
}

dependencies {
    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
    implementation(projects.libs.uikit)
    implementation(projects.libs.network)
    implementation(projects.libs.navigation)
    implementation(projects.libs.resources)
    implementation(projects.libs.fs.core)
}
