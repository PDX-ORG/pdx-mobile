@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.jvm)
}

dependencies {
    implementation(libs.akore.blogger.core)
    api(libs.kotlin.collections.immutable)
    api(libs.arrow.core)
    api(libs.kotlin.datetime)
    api(libs.kotlin.coroutines)
}
