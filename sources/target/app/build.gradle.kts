@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pdx.convention.target)
}

android {
    defaultConfig {
        applicationId = "io.github.lexadiky.pdx"
    }
}

dependencies {
    implementation(projects.library.uikit)
    implementation(projects.library.featureToggle)
    implementation(projects.library.network)
    implementation(projects.library.navigation)
    implementation(projects.library.system)
    implementation(projects.library.target)
    implementation(projects.domain.achievement)
    implementation(projects.domain.pokemonAsset)
    implementation(projects.features.toolbar)
    implementation(projects.features.drawer)
    implementation(projects.features.news)
    implementation(projects.features.settings)
    implementation(projects.features.rateApp)
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
