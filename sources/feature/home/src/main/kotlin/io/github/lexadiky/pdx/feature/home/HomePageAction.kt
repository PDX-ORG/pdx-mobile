package io.github.lexadiky.pdx.feature.home

import io.github.lexadiky.pdx.feature.home.entitiy.SuggestedPokemonItem
import io.github.lexadiky.pdx.feature.home.entitiy.SuggestedPokemonType

sealed interface HomePageAction {

    object HideError : HomePageAction

    sealed interface Navigate : HomePageAction {

        object PokemonList : Navigate

        object WhoIs : Navigate

        object News : Navigate

        object Achievements : Navigate

        object Types : Navigate

        object ApplicationShare : Navigate

        data class PokemonDetails(
            val item: SuggestedPokemonItem,
            val type: SuggestedPokemonType
        ): Navigate
    }
}
