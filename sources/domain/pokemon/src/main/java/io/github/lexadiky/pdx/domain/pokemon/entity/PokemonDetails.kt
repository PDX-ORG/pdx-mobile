package io.github.lexadiky.pdx.domain.pokemon.entity

data class PokemonDetails(
    val sprites: PokemonSprites,
    val types: List<PokemonType>
)