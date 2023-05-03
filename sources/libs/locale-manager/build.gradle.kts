plugins {
    alias(libs.plugins.pdx.convention.library.jvm)
}

dependencies {
    implementation(projects.libs.microdata)
    implementation(libs.akore.alice.core)
}
