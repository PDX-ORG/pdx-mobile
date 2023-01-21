package io.github.lexadiky.pdx.ui.uikit.image

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.get
import androidx.core.graphics.set

fun Bitmap.mapPixels(mapper: (Color) -> Color): Bitmap {
    (0 until width).forEach { x ->
        (0 until height).forEach { y ->
            val newPixel = mapper(Color(get(x, y)))
            set(x, y, newPixel.toArgb())
        }
    }

    return this
}