@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.libs.microdata)
    implementation(projects.libs.fs.core)
    implementation(projects.libs.localeManager)
    implementation(projects.libs.core)

    implementation(libs.kotlin.serialization.json)
    implementation(libs.akore.blogger.core)
    implementation(libs.akore.alice.core)
    implementation(libs.arrow.core)
    implementation(libs.pokeapi)
    implementation(libs.kotlin.reflection)
}
