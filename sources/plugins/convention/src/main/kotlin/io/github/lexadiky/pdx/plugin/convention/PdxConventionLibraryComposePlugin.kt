package io.github.lexadiky.pdx.plugin.convention

import io.github.lexadiky.pdx.plugin.convention.mixin.ComposeMixin
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("MagicNumber")
class PdxConventionLibraryComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("io.github.lexadiky.pdx.plugin.module.library.android")
        ComposeMixin.mix(target)
    }
}
