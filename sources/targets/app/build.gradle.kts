plugins {
    alias(libs.plugins.pdx.convention.target)
}

android {
    namespace = "io.github.lexadiky.pdx"

    defaultConfig {
        applicationId = "io.github.lexadiky.pdx"
    }
}

dependencies {
    implementation(projects.libs.uikit)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.network)
    implementation(projects.libs.navigation)
    implementation(projects.libs.target)
    implementation(projects.domain.achievement)
    implementation(projects.domain.pokemonAsset)
    implementation(projects.features.toolbar)
    implementation(projects.features.drawer)
    implementation(projects.features.news)
    implementation(projects.features.settings)
    implementation(projects.features.pokemon.list)
    implementation(projects.features.pokemon.details)
    implementation(projects.features.type.chart)
    implementation(projects.features.whois)
    implementation(projects.features.type.details)
    implementation(projects.features.home)
    implementation(projects.features.spriteGallery)
    implementation(projects.features.debugPanel)
    implementation(projects.features.abilityDetails)
    implementation(projects.features.move.details)

    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
}
