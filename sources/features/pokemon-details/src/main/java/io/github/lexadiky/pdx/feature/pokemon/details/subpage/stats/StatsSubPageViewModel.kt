package io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails

class StatsSubPageViewModel(
    pokemonSpeciesDetails: PokemonSpeciesDetails,
    selectedVariety: PokemonDetails
) : ViewModel() {

    var state by mutableStateOf(StatsSubPageState())
        private set

    init {
        state = state.copy(
            baseStats = selectedVariety.stats.toSortedMap(Comparator.comparing { it.ordinal })
                .map { it.key to it.value },
            types = selectedVariety.types
        )
    }
}
