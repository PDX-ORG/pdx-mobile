@file:Suppress("DSL_SCOPE_VIOLATION")


plugins {
    alias(libs.plugins.pdx.convention.library.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
        implementation(projects.library.core)
        implementation(projects.library.microdata)
        implementation(libs.akore.alice.core)

}