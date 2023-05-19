package io.github.lexadiky.pdx.plugin.convention

import androidx.baselineprofile.gradle.consumer.BaselineProfileConsumerExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import io.github.lexadiky.pdx.plugin.convention.mixin.AndroidCommonMixin
import io.github.lexadiky.pdx.plugin.convention.mixin.ComposeMixin
import io.github.lexadiky.pdx.plugin.convention.mixin.DeshugaringMixin
import io.github.lexadiky.pdx.plugin.convention.mixin.TestMixin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.project

@Suppress("MagicNumber")
class PdxConventionTargetPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("com.android.application")
        target.plugins.apply("org.jetbrains.kotlin.android")
        target.plugins.apply("com.google.gms.google-services")
        target.plugins.apply("com.google.firebase.crashlytics")
        target.plugins.apply("com.google.firebase.firebase-perf")
        target.plugins.apply("org.jetbrains.kotlinx.kover")

        baselineSettings(target)

        AndroidCommonMixin.mix(target)
        target.extensions.configure<BaseAppModuleExtension> {
            androidSettings(target)
        }
        TestMixin.mix(target)
        ComposeMixin.mix(target)
        DeshugaringMixin.mix(target)
    }

    private fun baselineSettings(target: Project) {
        return // TODO will not apply
        if (target.childProjects.contains("baseline")) {
            target.plugins.apply("androidx.baselineprofile")
            with(target.extensions.findByType<BaselineProfileConsumerExtension>()!!) {
                saveInSrc = true
            }
            target.dependencies {
                add("baselineProfile", project("${target.path}:baseline"))
            }
        }
    }

    private fun BaseAppModuleExtension.androidSettings(target: Project) {
        defaultConfig {
            targetSdk = target.extra["pdx.android.target-sdk"].toString().toInt()
            versionCode = target.extra["pdx.version.code"].toString().toInt()
            versionName = target.extra["pdx.version.name"].toString()
        }
        lint {
            disable += setOf("Instantiatable")
        }
    }
}
