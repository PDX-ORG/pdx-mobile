package io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.getOrElse
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonAbilitiesUseCase
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonAbilityData
import kotlinx.coroutines.launch

internal class StatsSubPageViewModel(
    pokemonSpeciesDetails: PokemonSpeciesDetails,
    private val selectedVariety: PokemonDetails,
    private val getPokemonAbilities: GetPokemonAbilitiesUseCase,
    private val navigator: Navigator
) : ViewModel() {

    var state by mutableStateOf(StatsSubPageState())
        private set

    init {
        state = state.copy(
            baseStats = selectedVariety.stats.toSortedMap(Comparator.comparing { it.ordinal })
                .map { StatsSubPageState.StatDescription(it.key, it.value) },
            types = selectedVariety.types,
            archetype = selectedVariety.archetype,
        )
        loadAbilities()
    }

    fun openAbilityDetails(abilityData: PokemonAbilityData) = viewModelScope.launch {
        navigator.navigate("pdx://ability/${abilityData.name}")
    }

    private fun loadAbilities() = viewModelScope.launch {
        state = state.copy(
            abilities = getPokemonAbilities.invoke(selectedVariety).getOrElse { emptyList() }
                .map { PokemonAbilityData.from(it) }
        )
    }
}
