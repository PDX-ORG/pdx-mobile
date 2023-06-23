@file:Suppress("DSL_SCOPE_VIOLATION")

import io.github.lexadiky.pdx.plugin.convention.multiplatform.dependencies

plugins {
    alias(libs.plugins.pdx.convention.library.multiplatform)
}

dependencies {
    common {
        implementation(libs.akore.blogger.core)
        api(libs.kotlin.collections.immutable)
        api(libs.arrow.core)
        api(libs.kotlin.datetime)
        api(libs.kotlin.coroutines)
        api(libs.kotlin.serialization.json)
    }
}
