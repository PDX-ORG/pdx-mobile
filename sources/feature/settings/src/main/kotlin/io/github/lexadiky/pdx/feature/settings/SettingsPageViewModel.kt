package io.github.lexadiky.pdx.feature.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.domain.pokemon.entity.UseRomajiLocaleFlag
import io.github.lexadiky.pdx.library.locale.LocaleManager
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.nibbler.navigate
import io.github.lexadiky.pdx.library.uikit.theme.custom.CustomTheme
import io.github.lexadiky.pdx.library.uikit.theme.custom.ThemeManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class SettingsPageViewModel(
    private val themeManager: ThemeManager,
    private val navigator: Navigator,
    private val localeManager: LocaleManager
) : ViewModel() {

    var state by mutableStateOf(SettingsPageState())
        private set

    init {
        state = state.copy(
            availableThemes = themeManager.list(),
            romajiEnabled = localeManager.settings.has(UseRomajiLocaleFlag)
        )

        viewModelScope.launch {
            themeManager.observe().collectLatest { theme ->
                state = state.copy(currentTheme = theme)
            }
        }
    }

    fun onThemeSelected(theme: CustomTheme) = viewModelScope.launch {
        themeManager.set(theme.id)
    }

    fun dropCaches() = viewModelScope.launch {
        // TODO not implemented
    }

    fun openGithub() {
        viewModelScope.launch {
            navigator.navigate("https://github.com/PDX-ORG/pdx-mobile")
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
