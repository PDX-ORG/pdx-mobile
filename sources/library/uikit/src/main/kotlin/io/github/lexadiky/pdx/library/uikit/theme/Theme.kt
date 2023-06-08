package io.github.lexadiky.pdx.library.uikit.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.pdx.library.uikit.image.ProvideLocalImageLoader
import io.github.lexadiky.pdx.library.uikit.theme.custom.ThemeManager
import io.github.lexadiky.pdx.library.uikit.theme.custom.ThemeModule
import io.github.lexadiky.pdx.library.uikit.theme.custom.current
import io.github.lexadiky.pdx.library.uikit.util.scroll.ProvideLocalPrimeScrollState

@Composable
fun PdxTheme(
    content: @Composable () -> Unit
) {
    val isDarkTheme: Boolean = isSystemInDarkTheme()

    DIFeature(ThemeModule(isDarkTheme)) {
        val customTheme = di.inject<ThemeManager>().current()
        val colorScheme = customTheme.colorScheme

        UpdateStatusBarSideEffect(colorScheme, isDarkTheme)

        ProvideLocalPrimeScrollState {
            ProvideLocalImageLoader {
                MaterialTheme(
                    colorScheme = colorScheme,
                    content = content
                )
            }
        }
    }
}

@Composable
private fun UpdateStatusBarSideEffect(colorScheme: ColorScheme, isDarkTheme: Boolean) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDarkTheme
        }
    }
}
