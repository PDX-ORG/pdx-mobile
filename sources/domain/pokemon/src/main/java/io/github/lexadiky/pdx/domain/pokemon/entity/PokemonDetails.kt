package io.github.lexadiky.pdx.domain.pokemon.entity

data class PokemonDetails(
    val name: String,
    val sprites: PokemonSprites,
    val types: List<PokemonType>,
    val stats: Map<PokemonStat, Int>,
    val archetype: PokemonArchetype
)
