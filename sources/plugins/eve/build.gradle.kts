plugins {
    id("java-gradle-plugin")
    `kotlin-dsl`
}

dependencies {
    implementation("org.yaml:snakeyaml:2.0")
    implementation("com.squareup:kotlinpoet:1.12.0")
    implementation("org.jetbrains.kotlin.android:org.jetbrains.kotlin.android.gradle.plugin:1.8.0")
}

gradlePlugin {
    plugins {
        create("eve") {
            id = "io.github.lexadiky.pdx.plugin.eve"
            implementationClass = "io.github.lexadiky.pdx.plugin.eve.EvePlugin"
        }
    }
}
