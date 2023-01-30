plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.jvm")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(projects.libs.network)
    implementation(projects.libs.resources)
    implementation(libs.akore.blogger.core)
    implementation(libs.arrow.core)
    implementation(libs.pokeapi)
}