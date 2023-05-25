package io.github.lexadiky.pdx.library.uikit.util

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.android.material.color.utilities.Scheme

@Composable
@SuppressLint("RestrictedApi")
fun createColorSchemeFromColor(color: Color?) = if (isSystemInDarkTheme()) {
    Scheme.dark(color?.toArgb() ?: Color.Red.toArgb()).toColorScheme()
} else {
    Scheme.light(color?.toArgb() ?: Color.Green.toArgb()).toColorScheme()
}
