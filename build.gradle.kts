import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("com.android.application").version("8.0.0-beta01").apply(false)
    id("com.android.library").version("8.0.0-beta01").apply(false)
    id("org.jetbrains.kotlin.android").version("1.8.0").apply(false)
    id("org.jetbrains.kotlin.jvm").version("1.8.0").apply(false)
    kotlin("plugin.serialization").version("1.8.0").apply(false)

    id("io.github.lexadiky.pdx.plugin.eve").apply(false)

    id("io.gitlab.arturbosch.detekt").version("1.22.0")
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.2")
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
