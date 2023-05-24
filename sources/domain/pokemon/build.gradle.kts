@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.library.microdata)
    implementation(projects.library.fs.core)
    implementation(projects.library.localeManager)
    implementation(projects.library.core)

    implementation(libs.kotlin.serialization.json)
    implementation(libs.akore.blogger.core)
    implementation(libs.akore.alice.core)
    implementation(libs.arrow.core)
    implementation(libs.pokeapi)
    implementation(libs.kotlin.reflection)
}
