package io.github.lexadiky.pdx.feature.settings.achievement

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.domain.achievement.AchievementModule

val AchievementSettingsModule by module("achievement-settings") {
    import(AchievementModule)
    internal {
        singleViewModel { AchievementSettingsViewModel(inject()) }
    }
}
