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
    implementation(projects.feature.toolbar)
    implementation(projects.feature.drawer)
    implementation(projects.feature.news)
    implementation(projects.feature.settings)
    implementation(projects.feature.rateApp)
    implementation(projects.feature.pokemon.list)
    implementation(projects.feature.pokemon.details)
    implementation(projects.feature.type.chart)
    implementation(projects.feature.whois)
    implementation(projects.feature.type.details)
    implementation(projects.feature.home)
    implementation(projects.feature.spriteGallery)
    implementation(projects.feature.debugPanel)
    implementation(projects.feature.abilityDetails)
    implementation(projects.feature.move.details)

    implementation(libs.akore.alice.robo)
    implementation(libs.akore.blogger.core)
}
