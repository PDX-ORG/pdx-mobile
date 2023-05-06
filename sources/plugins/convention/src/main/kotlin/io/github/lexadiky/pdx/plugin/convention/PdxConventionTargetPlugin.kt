package io.github.lexadiky.pdx.plugin.convention

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import io.github.lexadiky.pdx.plugin.convention.mixin.AndroidCommonMixin
import io.github.lexadiky.pdx.plugin.convention.mixin.ComposeMixin
import io.github.lexadiky.pdx.plugin.convention.mixin.DeshugaringMixin
import io.github.lexadiky.pdx.plugin.convention.mixin.TestMixin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

@Suppress("MagicNumber")
class PdxConventionTargetPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("com.android.application")
        target.plugins.apply("org.jetbrains.kotlin.android")
        target.plugins.apply("com.google.gms.google-services")
        target.plugins.apply("com.google.firebase.crashlytics")
        target.plugins.apply("com.google.firebase.firebase-perf")


        AndroidCommonMixin.mix(target)
        target.extensions.findByType(BaseAppModuleExtension::class.java)!!
            .apply { androidSettings(target) }
        TestMixin.mix(target)
        ComposeMixin.mix(target)
        DeshugaringMixin.mix(target)
    }

    private fun BaseAppModuleExtension.androidSettings(target: Project) {
        defaultConfig {
            targetSdk = 33
            versionCode = target.extra["pdx.version.code"].toString().toInt()
            versionName = target.extra["pdx.version.name"].toString()
        }
    }
}
