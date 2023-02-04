package io.github.lexadiky.pdx.feature.home.entitiy

import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource

data class FeaturedPokemonItem(
    val name: StringResource,
    val image: ImageResource,
    val nationalDexId: StringResource,
    val id: String,
)