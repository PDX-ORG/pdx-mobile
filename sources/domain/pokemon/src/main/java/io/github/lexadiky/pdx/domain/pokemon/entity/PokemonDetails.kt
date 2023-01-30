package io.github.lexadiky.pdx.domain.pokemon.entity

import java.util.SortedMap

data class PokemonDetails(
    val sprites: PokemonSprites,
    val types: List<PokemonType>,
    val stats: Map<PokemonStat, Int>
)
