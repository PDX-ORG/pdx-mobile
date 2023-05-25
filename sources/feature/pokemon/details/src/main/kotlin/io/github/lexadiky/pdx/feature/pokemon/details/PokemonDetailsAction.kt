package io.github.lexadiky.pdx.feature.pokemon.details

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType

sealed interface PokemonDetailsAction {

    data class SelectVariety(val index: Int) : PokemonDetailsAction

    data class OpenTypeDetails(val type: PokemonType) : PokemonDetailsAction

    object ToggleFavorite : PokemonDetailsAction

    object HideError : PokemonDetailsAction


    sealed interface Navigate : PokemonDetailsAction {

        object Sprites : Navigate
    }
}
