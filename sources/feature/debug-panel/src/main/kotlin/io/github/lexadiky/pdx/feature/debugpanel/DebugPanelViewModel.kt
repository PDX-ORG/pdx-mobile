package io.github.lexadiky.pdx.feature.debugpanel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.domain.achievement.AchievementManager
import io.github.lexadiky.pdx.domain.achievement.library.DebugAchievement
import io.github.lexadiky.pdx.feature.debugpanel.util.KashpirovskyException
import kotlinx.coroutines.launch

internal class DebugPanelViewModel(
    private val achievementManager: AchievementManager,
    private val navigator: Navigator
) : ViewModel() {

    fun giveAchievement() {
        achievementManager.give(DebugAchievement())
    }

    fun openNavigationSchema() = viewModelScope.launch {
        navigator.navigate("pdx://debug/panel/navigation-schema")
    }

    fun throwKashpirovskyException() {
        throw KashpirovskyException()
    }
}
