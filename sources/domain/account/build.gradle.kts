@file:Suppress("DSL_SCOPE_VIOLATION")

import io.github.lexadiky.pdx.plugin.convention.multiplatform.dependencies

plugins {
    alias(libs.plugins.pdx.convention.library.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    common {
        implementation(projects.library.core)
        implementation(projects.library.microdata)
        implementation(libs.akore.alice.core)
    }
}