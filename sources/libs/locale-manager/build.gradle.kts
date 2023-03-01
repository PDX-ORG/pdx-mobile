plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.jvm")
}

dependencies {
    implementation(projects.libs.fs.core)
    implementation(libs.akore.alice.core)
}