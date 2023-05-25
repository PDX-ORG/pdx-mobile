package io.github.lexadiky.pdx.feature.settings

import io.github.lexadiky.pdx.library.uikit.theme.custom.CustomTheme

internal data class SettingsPageState(
    val availableThemes: List<CustomTheme> = emptyList(),
    val romajiEnabled: Boolean = false,
    val currentTheme: CustomTheme? = null
)
