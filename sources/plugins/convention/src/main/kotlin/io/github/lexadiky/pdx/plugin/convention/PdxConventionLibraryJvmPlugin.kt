package io.github.lexadiky.pdx.plugin.convention

import org.gradle.api.Plugin
import org.gradle.api.Project

class PdxConventionLibraryJvmPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("org.jetbrains.kotlin.jvm")
    }
}
