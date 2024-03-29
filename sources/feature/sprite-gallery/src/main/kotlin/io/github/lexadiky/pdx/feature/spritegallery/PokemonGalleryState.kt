package io.github.lexadiky.pdx.feature.spritegallery

import io.github.lexadiky.pdx.library.errorhandler.UIError
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.string.StringResource

data class PokemonGalleryState(
    val items: List<Pair<StringResource, ImageResource>> = emptyList(),
    val error: UIError? = null
)
