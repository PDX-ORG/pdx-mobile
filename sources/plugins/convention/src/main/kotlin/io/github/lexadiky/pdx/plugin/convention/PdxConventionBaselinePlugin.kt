package io.github.lexadiky.pdx.plugin.convention

import androidx.baselineprofile.gradle.producer.BaselineProfileProducerExtension
import com.android.build.api.dsl.ManagedVirtualDevice
import com.android.build.gradle.TestExtension
import io.github.lexadiky.pdx.plugin.convention.config.GradleManagedDevice
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

class PdxConventionBaselinePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("org.jetbrains.kotlin.android")
        target.plugins.apply("com.android.test")
        target.plugins.apply("androidx.baselineprofile")

        with(target.extensions.findByType<TestExtension>()!!) {
            namespace = "io.github.lexadiky.baselineprofile"
            compileSdk = 33

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            defaultConfig {
                minSdk = 28
                targetSdk = 33

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            targetProjectPath = target.parent!!.path

            GradleManagedDevice.all.forEach { managedDevice ->
                testOptions.managedDevices.devices.create(managedDevice.id, ManagedVirtualDevice::class.java) {
                    device = managedDevice.name
                    apiLevel = managedDevice.apiLevel
                    systemImageSource = managedDevice.systemImageSource
                }
            }
        }

        with(target.extensions.findByType<BaselineProfileProducerExtension>()!!) {
            managedDevices += GradleManagedDevice.all.map { it.id }
            useConnectedDevices = false
        }
    }
}
