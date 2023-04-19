package io.github.lexadiky.pdx.feature.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.domain.pokemon.entity.UseRomajiLocaleFlag
import io.github.lexadiky.pdx.lib.fs.FsManager
import io.github.lexadiky.pdx.lib.locale.LocaleManager
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.ui.uikit.theme.custom.CustomTheme
import io.github.lexadiky.pdx.ui.uikit.theme.custom.ThemeManager
import kotlinx.coroutines.launch

internal class SettingsPageViewModel(
    private val themeManager: ThemeManager,
    private val fsManager: FsManager,
    private val navigator: Navigator,
    private val localeManager: LocaleManager
) : ViewModel() {

    var state by mutableStateOf(SettingsPageState())
        private set

    init {
        state = state.copy(
            availableThemes = themeManager.list(),
            currentTheme = themeManager.current(),
            romajiEnabled = localeManager.settings.has(UseRomajiLocaleFlag)
        )
    }

    fun onThemeSelected(theme: CustomTheme) = viewModelScope.launch {
        themeManager.set(theme.id)
        state = state.copy(
            currentTheme = themeManager.current()
        )
    }

    fun dropCaches() = viewModelScope.launch {
        // TODO not implemented
    }

    fun openGithub() {
        viewModelScope.launch {
            navigator.navigate("https://github.com/PDX-ORG/pdx-mobile-android")
        }
    }

    fun openAchievements() {
        viewModelScope.launch {
            navigator.navigate("pdx://settings/achievements")
        }
    }

    fun switchRomaji(isEnabled: Boolean) = viewModelScope.launch {
        state = state.copy(romajiEnabled = isEnabled)
        if (isEnabled) {
            localeManager.addFlag(UseRomajiLocaleFlag)
        } else {
            localeManager.removeFlag(UseRomajiLocaleFlag)
        }
    }
}
