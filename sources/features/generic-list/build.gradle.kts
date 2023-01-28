plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.generic.list"
}

dependencies {
    implementation(projects.libs.aliceRobo)
    implementation(projects.libs.uikit)
    implementation(projects.libs.network)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.navigation)
    implementation(libs.akore.blogger)
    implementation(libs.arrow.core)

    implementation(projects.domain.pokemon)
    implementation(projects.domain.achievement)
}