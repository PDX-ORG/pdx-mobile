package io.github.lexadiky.pdx.plugin.convention

import io.github.lexadiky.pdx.plugin.convention.mixin.TestMixin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.extra
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

class PdxConventionLibraryJvmPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("org.jetbrains.kotlin.jvm")
        target.plugins.apply("org.jetbrains.kotlinx.kover")

        target.extensions.configure<KotlinProjectExtension>() {
            jvmToolchain(target.extra["pdx.kotlin.jvm-toolchain"].toString().toInt())
        }

        TestMixin.mix(target)
    }
}
