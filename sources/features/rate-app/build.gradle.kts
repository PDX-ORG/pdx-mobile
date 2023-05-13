@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.rateapp"
}

dependencies {
    implementation(projects.libs.uikit)
    implementation(projects.libs.microdata)
    implementation(projects.libs.arc)

    implementation(libs.akore.alice.robo)
    implementation(libs.android.play.review)
}
