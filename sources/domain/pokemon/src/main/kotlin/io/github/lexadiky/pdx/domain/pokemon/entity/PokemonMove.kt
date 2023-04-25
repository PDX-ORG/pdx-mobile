package io.github.lexadiky.pdx.domain.pokemon.entity

data class PokemonMove(
    val name: String,
    val localeName: String,
    val localeFlavourText: String?,
    val type: PokemonType,
    val pp: Int?
)