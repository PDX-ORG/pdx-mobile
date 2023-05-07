package io.github.lexadiky.pdx.plugin.convention

import io.github.lexadiky.pdx.plugin.convention.mixin.TestMixin
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("MagicNumber")
class PdxConventionFeaturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("io.github.lexadiky.pdx.plugin.module.library.compose")
        target.plugins.apply("org.jetbrains.kotlinx.kover")

        TestMixin.mix(target)
    }
}
