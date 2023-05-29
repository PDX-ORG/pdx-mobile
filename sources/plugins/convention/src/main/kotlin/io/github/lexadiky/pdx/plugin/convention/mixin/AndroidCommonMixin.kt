package io.github.lexadiky.pdx.plugin.convention.mixin

import io.github.lexadiky.pdx.plugin.convention.android
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

@Suppress("MagicNumber")
object AndroidCommonMixin {

    fun mix(target: Project) {
        target.extensions.android.apply {
            compileSdk = target.extra["pdx.android.target-sdk"].toString().toInt()

            val namespaceSuffix = createNamespaceSuffix(target)
            namespace = "io.github.lexadiky.pdx.$namespaceSuffix"

            defaultConfig {
                minSdk = target.extra["pdx.android.min-sdk"].toString().toInt()

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }
            }

            buildTypes.findByName("release")?.apply {
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
                isCoreLibraryDesugaringEnabled = true
            }
            packaging {
                resources {
                    excludes += listOf(
                        "/META-INF/{AL2.0,LGPL2.1}",
                        "/META-INF/versions/9/previous-compilation-data.bin"
                    )
                }
            }
        }
    }

    private fun createNamespaceSuffix(target: Project) =
        target.path.replace(':', '.')
            .replace("-", "")
            .substring(1)
}
