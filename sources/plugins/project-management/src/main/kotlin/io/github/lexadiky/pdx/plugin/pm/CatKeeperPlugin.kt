package io.github.lexadiky.pdx.plugin.pm

import java.io.FileFilter
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class CatKeeperPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.tasks.register("catkeep") {
            doLast {
                fixGitIgnore()
                notifyWrongModuleStructure()
            }
        }
    }

    private fun Task.notifyWrongModuleStructure() {
        project.subprojects.forEach { subproject ->
            val projectDirectory = subproject.layout.projectDirectory
            if (projectDirectory.file("build.gradle.kts").asFile.exists()) {
                projectDirectory.asFile.listFiles(FileFilter { !it.isHidden })?.forEach { file ->
                    require(file.name in ALLOWED_FILES_IN_MODULE) {
                        "module ${subproject.name} contains illegal file ${file.name}"
                    }
                }
            }
        }
    }

    private fun Task.fixGitIgnore() {
        project.subprojects.forEach { subproject ->
            val projectDirectory = subproject.layout.projectDirectory

            val gitignoreFile = projectDirectory.dir(".gitignore")
                .asFile

            if (projectDirectory.file("build.gradle.kts").asFile.exists()) {
                gitignoreFile.writeText(
                    """
                        /build
                
                    """.trimIndent()
                )
            }
        }
    }

    companion object {

        private val ALLOWED_FILES_IN_MODULE = setOf(
            "src",
            ".gitignore",
            "build",
            "build.gradle.kts",
            "google-services.json"
        )
    }
}