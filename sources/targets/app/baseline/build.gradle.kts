plugins {
    id(libs.plugins.pdx.convention.target.baseline.get().pluginId)
}


dependencies {
    implementation(libs.android.benchmark.macro.junit4)
    implementation(libs.android.test.junit4)
    implementation(libs.android.test.espresso)
}

