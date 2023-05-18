package io.github.lexadiky.pdx.plugin.convention.multiplatform

import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

class MultiplatformDependencyWizard(private val target: Project) {

    fun common(config: KotlinDependencyHandler.() -> Unit) {
        with(target.extensions.findByType<KotlinMultiplatformExtension>()!!) {
            sourceSets.named("commonMain") {
                dependencies {
                    config()
                }
            }
        }
    }
}

fun Project.dependencies(configuration: MultiplatformDependencyWizard.() -> Unit) {
    MultiplatformDependencyWizard(this)
        .apply(configuration)
}