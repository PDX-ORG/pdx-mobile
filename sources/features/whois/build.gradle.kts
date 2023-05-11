@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.feature)
}

android {
    namespace = "io.github.lexadiky.pdx.feature.whois"
}

dependencies {
    implementation(projects.domain.pokemon)
    implementation(projects.domain.pokemonAsset)
    implementation(projects.domain.achievement)

    implementation(libs.akore.alice.robo)
    implementation(projects.libs.uikit)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.navigation)
    implementation(projects.libs.microdata)
    implementation(projects.libs.fs.core)

    implementation(libs.akore.blogger.core)
    implementation(libs.arrow.core)
}
