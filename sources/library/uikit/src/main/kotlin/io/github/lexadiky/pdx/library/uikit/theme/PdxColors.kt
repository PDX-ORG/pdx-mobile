package io.github.lexadiky.pdx.library.uikit.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
object PdxColors {

    val externalBrands = Brands

    val link = Color(0xFF2E86D3)

    object Brands {

        val reddit = Color(0xFFFF6314)
    }
}

@Suppress("UnusedReceiverParameter")
val ColorScheme.pdx get() = PdxColors


/**
 * Alias for accessing [Color.Transparent]
 */
val ColorScheme.transparent: Color get() = Color.Transparent
