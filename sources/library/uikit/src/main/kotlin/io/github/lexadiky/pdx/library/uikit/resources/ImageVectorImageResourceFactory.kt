@file:OptIn(DelicateResourcesApi::class)

package io.github.lexadiky.pdx.library.uikit.resources

import androidx.compose.ui.graphics.vector.ImageVector
import io.github.lexadiky.pdx.library.resources.DelicateResourcesApi
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.ImageVectorImageResource

fun ImageResource.Companion.from(vector: ImageVector): ImageResource = ImageVectorImageResource(vector)

val ImageVectorImageResource.vector: ImageVector get() = internalVector as ImageVector
