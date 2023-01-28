package io.github.lexadiky.pdx.feature.pokemon.details

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from

data class PokemonDetailsState(
    val id: String,
    val pokemonSpeciesDetails: PokemonSpeciesDetails? = null,
    val selectedVariety: PokemonDetails? = null,
    val error: UIError? = null
) {
    val availableVarieties: Int get() = pokemonSpeciesDetails?.varieties?.size ?: 0

    val name: StringResource? = pokemonSpeciesDetails?.localizedNames
        ?.get(PokemonLanguage.ENGLISH)
        ?.let { StringResource.from(it) }

    val image: ImageResource? = selectedVariety?.sprites?.default
        ?.let { ImageResource.from(it) }

    val types: List<PokemonType> get() = selectedVariety?.types.orEmpty()
}