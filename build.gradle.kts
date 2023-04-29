import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)

    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.jvm).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)

    alias(libs.plugins.detekt).apply(false)
    alias(libs.plugins.buildHealth).apply(false)

    id("io.github.lexadiky.pdx.plugin.pm.catkeeper")
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
