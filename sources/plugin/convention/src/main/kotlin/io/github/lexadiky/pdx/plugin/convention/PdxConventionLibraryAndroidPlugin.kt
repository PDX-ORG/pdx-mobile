package io.github.lexadiky.pdx.plugin.convention

import io.github.lexadiky.pdx.plugin.convention.mixin.AndroidCommonMixin
import io.github.lexadiky.pdx.plugin.convention.mixin.DeshugaringMixin
import io.github.lexadiky.pdx.plugin.convention.mixin.TestMixin
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("MagicNumber")
class PdxConventionLibraryAndroidPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("com.android.library")
        target.plugins.apply("org.jetbrains.kotlin.android")
        target.plugins.apply("org.jetbrains.kotlinx.kover")

        AndroidCommonMixin.mix(target)
        TestMixin.mix(target)
        DeshugaringMixin.mix(target)
    }
}
