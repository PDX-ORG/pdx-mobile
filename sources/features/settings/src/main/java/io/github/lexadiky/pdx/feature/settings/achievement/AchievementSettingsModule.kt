package io.github.lexadiky.pdx.feature.settings.achievement

import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.akore.alice.module

val AchievementSettingsModule by module("achievement-settings") {
    viewModel { AchievementSettingsViewModel(inject()) }
}