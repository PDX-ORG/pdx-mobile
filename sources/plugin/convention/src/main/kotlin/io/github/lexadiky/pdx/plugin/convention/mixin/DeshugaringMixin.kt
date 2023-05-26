package io.github.lexadiky.pdx.plugin.convention.mixin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

object DeshugaringMixin {

    fun mix(target: Project) {
        target.dependencies {
            add("coreLibraryDesugaring", "com.android.tools:desugar_jdk_libs:2.0.3")
        }
    }
}
