plugins {
    id("java-gradle-plugin")
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.plugin.android)
    implementation(libs.android.agp)
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
