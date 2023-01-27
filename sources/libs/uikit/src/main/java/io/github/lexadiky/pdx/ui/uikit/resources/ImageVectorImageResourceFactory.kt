package io.github.lexadiky.pdx.ui.uikit.resources

import androidx.compose.ui.graphics.vector.ImageVector
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.ImageVectorImageResource

fun ImageResource.Companion.from(vector: ImageVector): ImageResource = ImageVectorImageResource(vector)

val ImageVectorImageResource.vector: ImageVector get() = internalVector as ImageVector
