import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)

    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.jvm).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)

    alias(libs.plugins.detekt)
    alias(libs.plugins.buildHealth)
    alias(libs.plugins.pdx.catkeeper)
    alias(libs.plugins.kotlin.kover)
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.5")
        classpath("com.google.firebase:perf-plugin:1.4.2")
        classpath("com.autonomousapps.dependency-analysis:com.autonomousapps.dependency-analysis.gradle.plugin:1.20.0")
    }
}

tasks.register("detektAll", Detekt::class.java) {
    buildUponDefaultConfig = true
    parallel = true
    ignoreFailures = true

    config.setFrom(files("detekt-config.yaml"))

    setSource(files(projectDir))

    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")

    reports {
        sarif.required.set(true)
    }
}

dependencies {
    subprojects
        .filter { sub -> sub.plugins.hasPlugin(libs.plugins.kotlin.kover.get().pluginId) }
        .forEach { sub ->
            kover(project(sub.path))
        }
}
