package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails

@Suppress("UnusedPrivateMember") // TODO remove
class EvolutionSubPageViewModel(
    pokemonSpeciesDetails: PokemonSpeciesDetails,
    pokemonDetails: PokemonDetails
) : ViewModel() {

    var state by mutableStateOf(EvolutionSubPageState())
        private set
}
