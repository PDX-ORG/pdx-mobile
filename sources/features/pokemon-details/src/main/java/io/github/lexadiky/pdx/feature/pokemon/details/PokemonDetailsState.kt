package io.github.lexadiky.pdx.feature.pokemon.details

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource

data class PokemonDetailsState(
    val id: String,
    val name: StringResource? = null,
    val image: ImageResource? = null,
    val types: List<PokemonType> = emptyList(),
    val error: UIError? = null
)