package io.github.lexadiky.pdx.plugin.convention

import androidx.baselineprofile.gradle.producer.BaselineProfileProducerExtension
import com.android.build.api.dsl.ManagedVirtualDevice
import com.android.build.gradle.TestExtension
import io.github.lexadiky.pdx.plugin.convention.config.GradleManagedDevice
import io.github.lexadiky.pdx.plugin.convention.mixin.AndroidCommonMixin
import io.github.lexadiky.pdx.plugin.convention.mixin.DeshugaringMixin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.extra

class PdxConventionBaselinePlugin : Plugin<Project> {

    @Suppress("UnstableApiUsage")
    override fun apply(target: Project) {
        target.plugins.apply("org.jetbrains.kotlin.android")
        target.plugins.apply("com.android.test")
        target.plugins.apply("androidx.baselineprofile")

        DeshugaringMixin.mix(target)
        AndroidCommonMixin.mix(target)

        target.extensions.configure<TestExtension> {
            namespace = "io.github.lexadiky.baselineprofile"

            defaultConfig {
                minSdk = target.extra["pdx.android.baseline.min-sdk"].toString().toInt()
            }

            targetProjectPath = target.parent!!.path

            GradleManagedDevice.values().forEach { managedDevice ->
                testOptions.managedDevices.devices.create(managedDevice.id, ManagedVirtualDevice::class.java) {
                    device = managedDevice.readable
                    apiLevel = managedDevice.apiLevel
                    systemImageSource = managedDevice.systemImageSource
                }
            }
        }

        target.extensions.configure<BaselineProfileProducerExtension> {
            managedDevices += GradleManagedDevice.values().map { it.id }
            useConnectedDevices = false
        }
    }
}
