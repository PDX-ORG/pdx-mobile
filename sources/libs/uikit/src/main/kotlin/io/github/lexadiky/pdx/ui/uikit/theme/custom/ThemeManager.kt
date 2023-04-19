package io.github.lexadiky.pdx.ui.uikit.theme.custom

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.akore.blogger.info
import io.github.lexadiky.akore.blogger.verbose
import io.github.lexadiky.pdx.lib.microdata.MicrodataManager

class ThemeManager(
    private val context: Context,
    private val microdataManager: MicrodataManager,
    private val isDark: Boolean,
) {
    private val selectedThemeId = microdataManager.acquire(this, "custom_theme")
        .string("theme_id_${if (isDark) "dark" else "light"}")

    private val factory: CustomThemeFactory = CustomThemeFactory(context)
    private val currentTheme: MutableState<CustomTheme> = mutableStateOf(factory.default(isDark))

    init {
        BLogger.tag("ThemeManager").verbose("created")
        selectedThemeId.get()?.let { id ->
            val theme = list().firstOrNull { it.id == id && it.isDark == isDark }
            if (theme == null) {
                BLogger.tag("ThemeManager").error("can't find theme: '$id' to set, doing nothing")
            } else {
                currentTheme.value = theme
                BLogger.tag("ThemeManager").info("theme changed to: `$id`")
            }
        }
    }

    fun list(): List<CustomTheme> {
        return factory.create().filter { it.isDark == isDark }
    }

    suspend fun set(id: String): Boolean {
        val theme = list().firstOrNull { it.id == id && it.isDark == isDark }
        if (theme == null) {
            BLogger.tag("ThemeManager").error("can't find theme: '$id' to set, doing nothing")
            return false
        }
        selectedThemeId.set(id)
        currentTheme.value = theme
        BLogger.tag("ThemeManager").info("theme changed to: `$id`")
        return true
    }

    fun current(): CustomTheme {
        return currentTheme.value
    }
}
