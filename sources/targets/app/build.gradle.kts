plugins {
    id("io.github.lexadiky.pdx.plugin.module.target")
}

android {
    namespace = "io.github.lexadiky.pdx"

    defaultConfig {
        applicationId = "io.github.lexadiky.pdx"
    }
}

dependencies {
    implementation(projects.libs.uikit)
    implementation(projects.libs.aliceRobo)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.network)
    implementation(projects.libs.navigation)
    implementation(projects.libs.analytics)
    implementation(libs.akore.blogger)
    implementation(projects.libs.target)
    implementation(projects.domain.achievement)
    implementation(projects.features.toolbar)
    implementation(projects.features.drawer)
    implementation(projects.features.news)
    implementation(projects.features.settings)
    implementation(projects.features.pokemonList)
    implementation(projects.features.pokemonDetails)
    implementation(projects.features.typeChart)
    implementation(projects.features.whois)
    implementation(projects.features.typeDetails)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)

    testImplementation(libs.junit4.core)
    androidTestImplementation(libs.android.test.junit4)
    androidTestImplementation(libs.android.test.espresso)
    androidTestImplementation(libs.android.compose.ui.test)
    debugImplementation(libs.android.compose.ui.tooling)
    debugImplementation(libs.android.compose.ui.test.manifest)
}