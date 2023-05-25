package io.github.lexadiky.pdx.feature.home.entitiy

import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.string.StringResource

data class SuggestedPokemonItem(
    val name: StringResource,
    val image: ImageResource,
    val nationalDexId: StringResource,
    val id: String,
)
