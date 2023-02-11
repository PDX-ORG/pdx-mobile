package io.github.lexadiky.pdx

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.pdx.feature.settings.SettingsPage
import io.github.lexadiky.pdx.feature.settings.achievement.AchievementSettingsPage
import io.github.lexadiky.pdx.feature.typechart.TypePage
import io.github.lexadiky.pdx.lib.FeatureToggleManager
import io.github.lexadiky.pdx.lib.navigation.NaviNavGraphBuilder

@Composable
fun routing(): NaviNavGraphBuilder.() -> Unit {
    val toggleManager = di.inject<FeatureToggleManager>()

    return remember(toggleManager) {
        {
            page("pdx://settings") { SettingsPage() }
            page("pdx://settings/achievements") { AchievementSettingsPage() }
            page("pdx://type") { TypePage() }
        }
    }
}
