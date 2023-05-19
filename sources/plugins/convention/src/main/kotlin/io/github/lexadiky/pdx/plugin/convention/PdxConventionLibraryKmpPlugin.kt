package io.github.lexadiky.pdx.plugin.convention

import io.github.lexadiky.pdx.plugin.convention.mixin.TestMixin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class PdxConventionLibraryKmpPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("org.jetbrains.kotlin.multiplatform")
        target.plugins.apply("org.jetbrains.kotlinx.kover")

        target.extensions.configure<KotlinMultiplatformExtension> {
            jvm {
                jvmToolchain(17)
                withJava()
                testRuns.named("test") {
                    executionTask.configure {
                        useJUnitPlatform()
                    }
                }
            }
        }

        TestMixin.mix(target)
    }
}
