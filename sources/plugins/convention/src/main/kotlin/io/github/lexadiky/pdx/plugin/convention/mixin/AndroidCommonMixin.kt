package io.github.lexadiky.pdx.plugin.convention.mixin

import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.TestExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

@Suppress("MagicNumber")
object AndroidCommonMixin {

    fun mix(target: Project) {
        val android = target.extensions.findByType(LibraryExtension::class.java)
            ?: target.extensions.findByType(BaseAppModuleExtension::class.java)
            ?: target.extensions.findByType(TestExtension::class.java)
            ?: throw IllegalStateException("can't setup android, no suitable extensions found")

        android.apply {
            compileSdk = target.extra["pdx.android.target-sdk"].toString().toInt()

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
}
