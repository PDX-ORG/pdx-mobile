package io.github.lexadiky.pdx.plugin.convention.mixin

import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project

object ComposeMixin {

    fun mix(target: Project) {
        val android = target.extensions.findByType(LibraryExtension::class.java)
            ?: target.extensions.findByType(BaseAppModuleExtension::class.java)!!

        android.apply {
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = "1.4.4"
            }
        }
    }
}