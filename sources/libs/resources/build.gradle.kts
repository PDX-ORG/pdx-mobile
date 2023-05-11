@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.library.jvm)
}

dependencies {
    api(libs.kotlin.datetime)
}
