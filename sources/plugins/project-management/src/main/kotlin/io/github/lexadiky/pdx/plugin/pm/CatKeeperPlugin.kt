package io.github.lexadiky.pdx.plugin.pm

import io.github.lexadiky.pdx.plugin.pm.utils.MeaningfulFileFilter
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class CatKeeperPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.tasks.register("catkeep") {
            doLast {
                notifyWrongModuleStructure()
            }
        }
    }

    private fun Task.notifyWrongModuleStructure() {
        project.subprojects.forEach { subproject ->
            val projectDirectory = subproject.layout.projectDirectory
            if (projectDirectory.file("build.gradle.kts").asFile.exists()) {
                projectDirectory.asFile.listFiles(MeaningfulFileFilter)?.forEach { file ->
                    require(file.name in ALLOWED_FILES_IN_MODULE) {
                        "module ${subproject.name} contains illegal file ${file.name}"
                    }
                }
            }
        }
    }

    companion object {

        private val ALLOWED_FILES_IN_MODULE = setOf(
            "src",
            ".gitignore",
            "build",
            "build.gradle.kts",
            "google-services.json",
            "baseline",
            "proguard-rules.pro"
        )
    }
}
