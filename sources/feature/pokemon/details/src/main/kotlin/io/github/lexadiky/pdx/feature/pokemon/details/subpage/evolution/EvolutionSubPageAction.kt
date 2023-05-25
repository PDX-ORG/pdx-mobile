package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution

sealed interface EvolutionSubPageAction {

    sealed interface Navigate : EvolutionSubPageAction {

        class PokemonDetails(val speciesId: String) : Navigate
    }
}
