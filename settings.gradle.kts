pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS) // TODO required by KMP
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
    defaultLibrariesExtensionName.set("projectLibs")
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}
rootProject.name = "pdx-mobile"

includeBuild("sources/plugins")
includeRecursive("sources")

fun includeRecursive(path: String) {
    val dir = file(path)
    dir.walkTopDown().maxDepth(5).forEach { subDir ->
        if (isModule(subDir)) {
            val moduleName = createModuleName(subDir, dir)

            // TODO reenable baseline
            if (!moduleName.startsWith(":plugins") && !moduleName.endsWith(":baseline")) {
                include(moduleName)
                project(moduleName).projectDir = subDir
            }
        }
    }
}

fun isModule(dir: File): Boolean {
    return File(dir, "build.gradle").exists() || File(dir, "build.gradle.kts").exists()
}

fun createModuleName(subDir: File, dir: File): String {
    var moduleName = ":${subDir.name}"
    var currentDir = subDir.parentFile

    while (currentDir != null) {
        moduleName = ":${currentDir.name}" + moduleName

        currentDir = if (currentDir == dir) {
            null
        } else {
            currentDir.parentFile
        }
    }
    return moduleName.removePrefix(":sources")
}
