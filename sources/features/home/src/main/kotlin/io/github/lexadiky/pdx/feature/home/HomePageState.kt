package io.github.lexadiky.pdx.feature.home

import io.github.lexadiky.pdx.feature.home.entitiy.SuggestedPokemonItem
import io.github.lexadiky.pdx.lib.errorhandler.UIError

data class HomePageState(
    val featuredPokemon: List<SuggestedPokemonItem> = emptyList(),
    val latestViewedPokemon: List<SuggestedPokemonItem> = emptyList(),
    val error: UIError? = null
)
