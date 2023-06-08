package io.github.lexadiky.pdx.library.uikit.image.transformation

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import coil.size.Size
import coil.transform.Transformation
import io.github.lexadiky.pdx.library.uikit.image.mapPixels

class MaskImageTransformation(private val color: Color) : Transformation {

    override val cacheKey: String = "MaskImageTransformation"

    override suspend fun transform(input: Bitmap, size: Size): Bitmap = input.mapPixels { pixel ->
        if (pixel.alpha != 0f) {
            color
        } else {
            pixel
        }
    }
}
