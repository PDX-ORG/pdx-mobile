plugins {
    id("java-gradle-plugin")
    `kotlin-dsl`
}

dependencies {
    implementation("org.jetbrains.kotlin.android:org.jetbrains.kotlin.android.gradle.plugin:1.8.0")
    implementation("com.android.application:com.android.application.gradle.plugin:8.0.0-alpha10")
}

gradlePlugin {
    plugins {
        create("pdx-module-target") {
            id = "io.github.lexadiky.pdx.plugin.module.target"
            implementationClass = "io.github.lexadiky.pdx.plugin.convention.PdxConventionTargetPlugin"
        }
        create("pdx-module-feature") {
            id = "io.github.lexadiky.pdx.plugin.module.feature"
            implementationClass = "io.github.lexadiky.pdx.plugin.convention.PdxConventionFeaturePlugin"
        }
        create("pdx-module-library-android") {
            id = "io.github.lexadiky.pdx.plugin.module.library.android"
            implementationClass = "io.github.lexadiky.pdx.plugin.convention.PdxConventionLibraryAndroidPlugin"
        }
        create("pdx-module-library-jvm") {
            id = "io.github.lexadiky.pdx.plugin.module.library.jvm"
            implementationClass = "io.github.lexadiky.pdx.plugin.convention.PdxConventionLibraryJvmPlugin"
        }
    }
}