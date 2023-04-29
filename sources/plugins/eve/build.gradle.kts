plugins {
    id("java-gradle-plugin")
    `kotlin-dsl`
}

dependencies {
    implementation(libs.snakeyaml)
    implementation(libs.kotlinpoet)
    implementation(libs.kotlin.plugin.android)
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
