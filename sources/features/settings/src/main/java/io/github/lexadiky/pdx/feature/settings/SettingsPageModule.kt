package io.github.lexadiky.pdx.feature.settings

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.achievement.AchievementModule
import io.github.lexadiky.pdx.feature.settings.achievement.AchievementSettingsViewModel

val SettingsPageModule by module("settings") {
    import(AchievementModule)
    internal {
        viewModel { SettingsPageViewModel(inject(), inject(), inject()) }
        viewModel { AchievementSettingsViewModel(inject()) }
    }
}
