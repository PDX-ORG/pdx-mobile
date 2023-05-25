package io.github.lexadiky.pdx.library.uikit.theme.custom

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.akore.blogger.info
import io.github.lexadiky.akore.blogger.verbose
import io.github.lexadiky.pdx.library.microdata.MicrodataManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    fun default(): CustomTheme {
        return factory.default(isDark)
    }

    fun observe(): Flow<CustomTheme> = selectedThemeId.observe()
        .map { id -> list().firstOrNull { it.id == id && it.isDark == isDark } }
        .map { it ?: default() }
}

@Composable
fun ThemeManager.current(): CustomTheme {
    return observe().collectAsState(initial = remember { default() })
        .value
}
