package io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats

import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonAbilityData

sealed interface StatsSubPageAction {

    sealed interface Navigate : StatsSubPageAction {

        data class AbilityDetails(val data: PokemonAbilityData): Navigate
    }
}
