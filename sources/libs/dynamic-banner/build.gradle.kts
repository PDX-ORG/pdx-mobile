plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
    kotlin("plugin.serialization")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.dynbanner"
}

dependencies {
    implementation(projects.libs.uikit)
    implementation(projects.libs.network)
    implementation(projects.libs.navigation)
    implementation(projects.libs.resources)
    implementation(projects.libs.fs.core)
    implementation(projects.libs.core)
    implementation(projects.libs.errorHandler)

    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
    implementation(libs.kotlin.serialization.json)
}
