package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.entity

import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource

internal data class EvolutionLinkPokemonVR(
    val speciesId: String,
    val localName: StringResource,
    val image: ImageResource,
)
