package io.github.lexadiky.pdx.plugin.convention.mixin

import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get

object AndroidCommonMixin {

    fun mix(target: Project) {
        val android = target.extensions.findByType(LibraryExtension::class.java)
            ?: target.extensions.findByType(BaseAppModuleExtension::class.java)!!

        android.apply {
            compileSdk = 33

            defaultConfig {
                minSdk = 24

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }
            }

            buildTypes["release"].apply {
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
            packagingOptions {
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