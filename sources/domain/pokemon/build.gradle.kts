plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.android")
    kotlin("plugin.serialization")
}

android {
    namespace = "io.github.lexadiky.pdx.domain.pokemon"
}

dependencies {
    implementation(projects.libs.network)
    implementation(projects.libs.resources)
    implementation(projects.libs.blogger)
}