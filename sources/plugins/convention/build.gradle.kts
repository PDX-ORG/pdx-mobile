plugins {
    id("java-gradle-plugin")
    `kotlin-dsl`
}

dependencies {
    implementation(libs.classpath.kotlin.android)
    implementation(libs.classpath.android.agp)
    implementation(libs.classpath.android.baseline.profile)
}

gradlePlugin {
    plugins {
        create("pdx-module-target") {
            id = "io.github.lexadiky.pdx.plugin.module.target"
            implementationClass = "io.github.lexadiky.pdx.plugin.convention.PdxConventionTargetPlugin"
            version = "indev"
        }
        create("pdx-android-target-baseline") {
            id = "io.github.lexadiky.pdx.plugin.module.baseline"
            implementationClass = "io.github.lexadiky.pdx.plugin.convention.PdxConventionBaselinePlugin"
            version = "indev"
        }
        create("pdx-module-feature") {
            id = "io.github.lexadiky.pdx.plugin.module.feature"
            implementationClass = "io.github.lexadiky.pdx.plugin.convention.PdxConventionFeaturePlugin"
            version = "indev"
        }
        create("pdx-module-library-android") {
            id = "io.github.lexadiky.pdx.plugin.module.library.android"
            implementationClass = "io.github.lexadiky.pdx.plugin.convention.PdxConventionLibraryAndroidPlugin"
            version = "indev"
        }
        create("pdx-module-library-compose") {
            id = "io.github.lexadiky.pdx.plugin.module.library.compose"
            implementationClass = "io.github.lexadiky.pdx.plugin.convention.PdxConventionLibraryComposePlugin"
            version = "indev"
        }
        create("pdx-module-library-jvm") {
            id = "io.github.lexadiky.pdx.plugin.module.library.jvm"
            implementationClass = "io.github.lexadiky.pdx.plugin.convention.PdxConventionLibraryJvmPlugin"
            version = "indev"
        }
    }
}
