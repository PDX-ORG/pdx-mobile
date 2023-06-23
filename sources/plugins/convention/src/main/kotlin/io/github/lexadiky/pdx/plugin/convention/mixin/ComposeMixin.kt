package io.github.lexadiky.pdx.plugin.convention.mixin

import io.github.lexadiky.pdx.plugin.convention.android
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

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

        target.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            kotlinOptions {
                freeCompilerArgs += arrayOf(
                    "-Xallow-jvm-ir-dependencies",
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
                )
            }
        }
    }
}
