package io.github.lexadiky.pdx.plugin.convention

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("MagicNumber")
class PdxConventionTargetPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("com.android.application")
        target.plugins.apply("org.jetbrains.kotlin.android")
        target.plugins.apply("com.google.gms.google-services")
        target.plugins.apply("com.google.firebase.crashlytics")
        target.plugins.apply("com.google.firebase.firebase-perf")

        target.extensions.findByType(BaseAppModuleExtension::class.java)!!
            .apply { androidSettings() }

        TestMixin.mix(target)
    }

    private fun BaseAppModuleExtension.androidSettings() {
        compileSdk = 33

        defaultConfig {
            minSdk = 24
            targetSdk = 33
            versionCode = 3
            versionName = "0.1.2"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        buildTypes {
            release {
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            isCoreLibraryDesugaringEnabled = true
        }
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.4.4"
        }
        packagingOptions {
            resources {
                excludes += listOf(
                    "/META-INF/{AL2.0,LGPL2.1}",
                    "/META-INF/versions/9/previous-compilation-data.bin"
                )
            }
        }
        lint {
            disable += "Instantiatable"
        }
    }
}
