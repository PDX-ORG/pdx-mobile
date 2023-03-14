plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.jvm")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(projects.libs.network)
    implementation(projects.libs.resources)
    implementation(projects.libs.fs.core)
    implementation(projects.libs.localeManager)
    implementation(projects.libs.core)
    implementation(libs.akore.blogger.core)
    implementation(libs.arrow.core)
    implementation(libs.pokeapi)
    implementation(libs.kotlin.reflection)
}
