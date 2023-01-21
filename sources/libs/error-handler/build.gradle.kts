plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.errorhandler"
}

dependencies {
    implementation(projects.libs.arc)
    implementation(projects.libs.uikit)
    implementation(projects.libs.resources)
}