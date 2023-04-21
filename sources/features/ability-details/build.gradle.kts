plugins {
    id("io.github.lexadiky.pdx.plugin.module.feature")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.ability.details"
}

dependencies {
    implementation(projects.domain.pokemon)
    implementation(projects.libs.uikit)
    implementation(projects.libs.errorHandler)

    implementation(libs.akore.alice.robo)
    implementation(libs.arrow.core)
}
