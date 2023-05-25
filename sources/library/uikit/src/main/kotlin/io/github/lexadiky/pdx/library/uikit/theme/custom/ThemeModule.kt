package io.github.lexadiky.pdx.library.uikit.theme.custom

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.lexadiky.akore.alice.DIModule
import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single

@Composable
internal fun ThemeModule(context: Context, isDark: Boolean): Lazy<DIModule> = remember(context, isDark) {
    module("theme") {
        single { ThemeManager(inject(), inject(), isDark) }
    }
}
