package io.github.lexadiky.pdx.plugin.convention

import com.android.build.api.dsl.LibraryExtension
import io.github.lexadiky.pdx.plugin.convention.mixin.AndroidCommonMixin
import io.github.lexadiky.pdx.plugin.convention.mixin.DeshugaringMixin
import io.github.lexadiky.pdx.plugin.convention.mixin.TestMixin
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("MagicNumber")
class PdxConventionFeaturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("com.android.library")
        target.plugins.apply("org.jetbrains.kotlin.android")

        AndroidCommonMixin.mix(target)
        target.extensions.findByType(LibraryExtension::class.java)!!
            .apply { androidSettings() }
        TestMixin.mix(target)
        DeshugaringMixin.mix(target)
    }

    private fun LibraryExtension.androidSettings() {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.4.4"
        }
    }
}
