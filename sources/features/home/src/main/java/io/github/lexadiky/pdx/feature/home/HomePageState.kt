package io.github.lexadiky.pdx.feature.home

import io.github.lexadiky.pdx.feature.home.entitiy.FeaturedPokemonItem
import io.github.lexadiky.pdx.lib.errorhandler.UIError

data class HomePageState(
    val featuredPokemon: List<FeaturedPokemonItem> = emptyList(),
    val latestViewedPokemon: List<FeaturedPokemonItem> = emptyList(),
    val error: UIError? = null
)