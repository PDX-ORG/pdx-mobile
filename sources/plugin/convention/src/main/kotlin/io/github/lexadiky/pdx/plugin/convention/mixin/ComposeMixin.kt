package io.github.lexadiky.pdx.plugin.convention.mixin

import io.github.lexadiky.pdx.plugin.convention.android
import org.gradle.api.Project

object ComposeMixin {

    fun mix(target: Project) {
        target.extensions.android.apply {
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = "1.4.7"
            }
        }
    }
}
