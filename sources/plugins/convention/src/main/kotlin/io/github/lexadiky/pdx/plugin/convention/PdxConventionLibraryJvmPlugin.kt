package io.github.lexadiky.pdx.plugin.convention

import io.github.lexadiky.pdx.plugin.convention.mixin.TestMixin
import org.gradle.api.Plugin
import org.gradle.api.Project

class PdxConventionLibraryJvmPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("org.jetbrains.kotlin.jvm")
        target.plugins.apply("org.jetbrains.kotlinx.kover")

        TestMixin.mix(target)
    }
}
