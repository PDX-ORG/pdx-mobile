package io.github.lexadiky.pdx.library.uikit.theme.custom

import androidx.compose.runtime.Composable
import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single

@Composable
internal fun ThemeModule(isDark: Boolean) = eagerModule("library-theme") {
    module("theme") {
        single { ThemeManager(inject(), inject(), isDark) }
        internal {
            single { CustomThemeFactory(inject()) }
        }
    }
}
