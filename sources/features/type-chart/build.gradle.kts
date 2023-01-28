plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.typechart"
}

dependencies {
    implementation(projects.domain.pokemon)

    implementation(projects.libs.aliceRobo)
    implementation(projects.libs.uikit)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.navigation)

    implementation(libs.akore.blogger)
    implementation(libs.arrow.core)
}