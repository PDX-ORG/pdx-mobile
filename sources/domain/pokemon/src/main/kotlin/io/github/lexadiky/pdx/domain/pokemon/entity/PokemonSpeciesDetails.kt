package io.github.lexadiky.pdx.domain.pokemon.entity

data class PokemonSpeciesDetails(
    val name: String,
    val localeName: String,
    val primaryVariety: PokemonDetails,
    val varieties: List<PokemonDetails>,
    val isLegendary: Boolean,
    val isMythical: Boolean
)
