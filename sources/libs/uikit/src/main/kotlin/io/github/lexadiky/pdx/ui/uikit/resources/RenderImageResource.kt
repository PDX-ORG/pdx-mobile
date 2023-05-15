package io.github.lexadiky.pdx.ui.uikit.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import coil.transform.Transformation
import io.github.lexadiky.pdx.lib.bildconf.PdxBuildConfig
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.ImageVectorImageResource
import io.github.lexadiky.pdx.lib.resources.image.PlaceholderImageResource
import io.github.lexadiky.pdx.lib.resources.image.ResImageResource
import io.github.lexadiky.pdx.lib.resources.image.UrlImageResource
import io.github.lexadiky.pdx.lib.uikit.R
import io.github.lexadiky.pdx.ui.uikit.image.rememberAsyncImagePainter
import io.github.lexadiky.pdx.ui.uikit.image.transformation.CropTransparentTransformation
import io.github.lexadiky.pdx.ui.uikit.image.transformation.MaskImageTransformation

@Composable
fun ImageResource.render(transformations: List<ImageTransformation> = emptyList()): Painter {
    return when (this) {
        is ImageVectorImageResource -> rememberVectorPainter(image = this.vector)
        is UrlImageResource -> rememberAsyncImagePainter(this.url) {
            crossfade(true)
            transformations(prepareTransformations(transformations))
        }
        is ResImageResource -> painterResource(id = this.id)
        PlaceholderImageResource -> painterResource(id = R.drawable.uikit_ic_pokeball)
    }
}

sealed interface ImageTransformation {

    object CropTransparent : ImageTransformation
    class Mask(val color: Color): ImageTransformation
}

private fun prepareTransformations(transformations: List<ImageTransformation>) =
    transformations.map { transformation ->
        when (transformation) {
            ImageTransformation.CropTransparent -> CropTransparentTransformation
            is ImageTransformation.Mask -> MaskImageTransformation(transformation.color)
        }
    }.let(::enrichTransformations)

private fun enrichTransformations(base: List<Transformation>): List<Transformation> {
    return if (PdxBuildConfig.isGooglePlayDemo) {
        base + MaskImageTransformation(Color.Black.copy(alpha = 0.25f))
    } else {
        base
    }
}
