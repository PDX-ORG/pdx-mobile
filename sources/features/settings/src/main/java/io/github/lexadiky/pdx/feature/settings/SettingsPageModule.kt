package io.github.lexadiky.pdx.feature.settings

import io.github.lexadiky.pdx.domain.achievement.AchievementModule
import io.github.lexadiky.pdx.feature.settings.achievement.AchievementSettingsModule
import io.github.lexadiky.pdx.feature.settings.achievement.AchievementSettingsViewModel
import io.github.lexadiky.pdx.lib.arc.di.module

val SettingsPageModule by module("settings") {
    import(AchievementModule)
    internal {
        viewModel { SettingsPageViewModel(inject(), inject()) }
        viewModel { AchievementSettingsViewModel(inject()) }
    }
}