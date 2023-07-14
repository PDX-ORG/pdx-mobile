@file:Suppress("DSL_SCOPE_VIOLATION")

import io.github.lexadiky.pdx.plugin.convention.multiplatform.dependencies

plugins {
    alias(libs.plugins.pdx.convention.library.multiplatform)
}

dependencies {
    common {
        implementation(libs.akore.blogger.core)

        implementation(libs.kotlin.serialization.json)
        implementation(libs.android.mops.datastore)
    }
}
