plugins {
    id("io.github.lexadiky.pdx.plugin.module.library.jvm")
}

dependencies {
    implementation(libs.akore.blogger.core)
    api(libs.arrow.core)
    api(libs.kotlin.datetime)
    api(libs.kotlin.coroutines)
}