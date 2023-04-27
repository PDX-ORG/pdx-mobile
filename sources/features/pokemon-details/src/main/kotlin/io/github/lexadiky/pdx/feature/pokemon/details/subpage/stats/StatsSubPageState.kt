package io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonArchetype
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonStat
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonAbilityData
import io.github.lexadiky.pdx.lib.core.lce.DynamicLceList
import kotlinx.coroutines.flow.emptyFlow

data class StatsSubPageState(
    val baseStats: List<StatDescription> = emptyList(),
    val types: List<PokemonType> = emptyList(),
    val abilities: DynamicLceList<*, PokemonAbilityData> = emptyFlow(),
    val archetype: PokemonArchetype? = null,
) {

    val totalBaseStatValue = baseStats.sumOf { it.value }

    data class StatDescription(
        val stat: PokemonStat,
        val value: Int
    ) {

        val normalValue: Float = value.toFloat() / MAX_VALUE

        companion object {

            private const val MAX_VALUE = 225
        }
    }
}
