plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.typechart"
}

dependencies {
    implementation(projects.domain.pokemon)

    implementation(projects.libs.arc)
    implementation(projects.libs.uikit)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.navigation)
    implementation(projects.libs.blogger)
}