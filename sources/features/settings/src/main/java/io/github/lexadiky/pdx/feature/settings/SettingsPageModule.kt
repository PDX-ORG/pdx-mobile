package io.github.lexadiky.pdx.feature.settings

import io.github.lexadiky.pdx.domain.achievement.AchievementModule
import io.github.lexadiky.pdx.feature.settings.achievement.AchievementSettingsModule
import io.github.lexadiky.pdx.feature.settings.achievement.AchievementSettingsViewModel
import io.github.lexadiky.pdx.lib.arc.di.module
import io.github.lexadiky.pdx.lib.fs.FsModule

val SettingsPageModule by module("settings") {
    import(AchievementModule)
    import(FsModule)
    internal {
        viewModel { SettingsPageViewModel(inject(), inject(), inject()) }
        viewModel { AchievementSettingsViewModel(inject()) }
    }
}