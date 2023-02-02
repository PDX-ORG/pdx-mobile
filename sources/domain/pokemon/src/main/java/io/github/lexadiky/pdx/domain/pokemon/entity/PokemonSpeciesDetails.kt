package io.github.lexadiky.pdx.domain.pokemon.entity

data class PokemonSpeciesDetails(
    val localizedNames: Map<PokemonLanguage, String>,
    val primaryVariety: PokemonDetails,
    val varieties: List<PokemonDetails>
)