@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("java-gradle-plugin")
    `kotlin-dsl`
}

dependencies {
    implementation(libs.classpath.kotlin.android)
    implementation(libs.snakeyaml)
    implementation(libs.kotlinpoet)
}

gradlePlugin {
    plugins {
        create("eve") {
            id = "io.github.lexadiky.pdx.plugin.eve"
            implementationClass = "io.github.lexadiky.pdx.plugin.eve.EvePlugin"
            version = "indev"
        }
    }
}
