package io.github.lexadiky.pdx.plugin.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("MagicNumber")
class PdxConventionFeaturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("com.android.library")
        target.plugins.apply("org.jetbrains.kotlin.android")

        target.extensions.findByType(LibraryExtension::class.java)!!
            .apply { androidSettings() }
    }

    private fun LibraryExtension.androidSettings() {
        compileSdk = 33

        defaultConfig {
            minSdk = 24

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        buildTypes {
            release {
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.4.0"
        }
        buildFeatures {
            compose = true
        }
        packagingOptions {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}
