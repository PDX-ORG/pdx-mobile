package io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonStat
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType

data class StatsSubPageState(
    val baseStats: List<Pair<PokemonStat, Int>> = emptyList(),
    val types: List<PokemonType> = emptyList()
) {

    val totalBaseStatValue = baseStats.sumOf { it.second }
}