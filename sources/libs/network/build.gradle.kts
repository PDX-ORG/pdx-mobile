@file:Suppress("DSL_SCOPE_VIOLATION")

import io.github.lexadiky.pdx.plugin.convention.multiplatform.dependencies

plugins {
    alias(libs.plugins.pdx.convention.library.multiplatform)
}

dependencies {
    common {
        api(libs.ktor.core)

        implementation(libs.ktor.okhttp)
        implementation(libs.ktor.json)
        implementation(libs.ktor.contentNegotiation)

        implementation(libs.akore.alice.core)
    }
}
