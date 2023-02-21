package io.github.lexadiky.pdx.feature.debugpanel

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.domain.achievement.AchievementModule

val DebugPanelPageModule by module("feature-debug-panel") {
    import(AchievementModule)
    internal {
        single { DebugPanelViewModel(inject(), inject()) }
    }
}
