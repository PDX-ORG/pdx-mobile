package io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonAbility
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonArchetype
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonStat
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonAbilityData

data class StatsSubPageState(
    val baseStats: List<Pair<PokemonStat, Int>> = emptyList(),
    val types: List<PokemonType> = emptyList(),
    val abilities: List<PokemonAbilityData> = emptyList(),
    val archetype: PokemonArchetype? = null,
) {

    val totalBaseStatValue = baseStats.sumOf { it.second }
}
