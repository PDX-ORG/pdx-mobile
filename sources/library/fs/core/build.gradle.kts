@file:Suppress("DSL_SCOPE_VIOLATION")

import io.github.lexadiky.pdx.plugin.convention.multiplatform.dependencies

plugins {
    alias(libs.plugins.pdx.convention.library.multiplatform)
}

dependencies {
    common {
        api(libs.arrow.core)
    }
}
