package io.github.lexadiky.pdx.plugin.convention

import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

object TestMixin {

    fun mix(target: Project) {
        target.tasks.withType<Test> {
            useJUnitPlatform()
        }

        target.dependencies {
            add("testImplementation", "org.junit.jupiter:junit-jupiter-api:5.9.2")
            add("testImplementation","org.junit.jupiter:junit-jupiter-params:5.9.2")
            add("testRuntimeOnly", "org.junit.jupiter:junit-jupiter-engine:5.9.2")
        }
    }
}
