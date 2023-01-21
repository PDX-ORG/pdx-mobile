package io.github.lexadiky.pdx.ui.uikit.theme.custom

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.github.lexadiky.lib.blogger.BLogger
import io.github.lexadiky.lib.blogger.error
import io.github.lexadiky.lib.blogger.info
import io.github.lexadiky.lib.blogger.verbose
import io.github.lexadiky.pdx.lib.fs.FsManager

class ThemeManager(
    private val context: Context,
    private val fsManager: FsManager,
    private val isDark: Boolean,
) {
    private var selectedThemeId by fsManager.state("custom_theme").string(
        key = "theme_id_${if (isDark) "dark" else "light"}",
        default = CustomThemeFactory.dynamicThemeId(isDark)
    )

    private val factory: CustomThemeFactory = CustomThemeFactory(context)
    private val currentTheme: MutableState<CustomTheme> = mutableStateOf(factory.default(isDark))

    init {
        BLogger.tag("ThemeManager").verbose("created")
        set(selectedThemeId)
    }

    fun list(): List<CustomTheme> {
        return factory.create().filter { it.isDark == isDark }
    }

    fun set(id: String): Boolean {
        val theme = list().firstOrNull { it.id == id && it.isDark == isDark }
        if (theme == null) {
            BLogger.tag("ThemeManager").error("can't find theme: '$id' to set, doing nothing")
            return false
        }
        selectedThemeId = id
        currentTheme.value = theme
        BLogger.tag("ThemeManager").info("theme changed to: `$id`")
        return true
    }

    fun current(): CustomTheme {
        return currentTheme.value
    }
}