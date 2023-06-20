package io.github.lexadiky.pdx.library.uikit.theme.custom

import io.github.lexadiky.akore.alice.DIModule
import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.util.single

internal fun ThemeModule(isDark: Boolean): DIModule = eagerModule("library-theme") {
    single { ThemeManager(inject(), inject(), isDark) }
    internal {
        single { CustomThemeFactory(inject()) }
    }
}
