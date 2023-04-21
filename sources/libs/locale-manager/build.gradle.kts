plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.jvm")
}

dependencies {
    implementation(projects.libs.microdata)
    implementation(libs.akore.alice.core)
}
