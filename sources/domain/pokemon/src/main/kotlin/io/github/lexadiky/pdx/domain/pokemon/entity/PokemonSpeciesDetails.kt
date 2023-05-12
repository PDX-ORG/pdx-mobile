package io.github.lexadiky.pdx.domain.pokemon.entity

data class PokemonSpeciesDetails(
    val name: String,
    val nationalDexNumber: Int,
    val localeName: String,
    val primaryVariety: PokemonDetails,
    val varieties: List<PokemonDetails>,
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
            simpleSearchIndex = "" // TODO not implemented
        )
    }
}
