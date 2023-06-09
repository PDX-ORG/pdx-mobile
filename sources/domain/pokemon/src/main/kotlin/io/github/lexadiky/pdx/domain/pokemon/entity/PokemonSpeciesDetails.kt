package io.github.lexadiky.pdx.domain.pokemon.entity

import io.github.lexadiky.pdx.library.core.fts.FtsIndex

data class PokemonSpeciesDetails(
    val name: String,
    val nationalDexNumber: Int,
    val localeName: String,
    val primaryVariety: PokemonDetails,
    val availableVarietiesCount: Int,
    val isLegendary: Boolean,
    val isMythical: Boolean
) {

    fun asPreview(): PokemonPreview {
        return PokemonPreview(
            localeName = localeName,
            name = name,
            nationalDexNumber = nationalDexNumber,
            normalSprite = primaryVariety.sprites.default,
            shinySprite = primaryVariety.sprites.defaultShiny,
            types = primaryVariety.types,
            searchIndex = FtsIndex.build(emptyList()) // TODO not implemented
        )
    }
}
