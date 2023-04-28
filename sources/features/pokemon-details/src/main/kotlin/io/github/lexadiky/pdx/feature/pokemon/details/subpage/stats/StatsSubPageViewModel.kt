package io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.usecase.ability.GetPokemonAbilitiesUseCase
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonAbilityData
import io.github.lexadiky.pdx.lib.core.lce.mapLce
import io.github.lexadiky.pdx.lib.errorhandler.classify
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class StatsSubPageViewModel(
    private val selectedVariety: PokemonDetails,
    private val getPokemonAbilities: GetPokemonAbilitiesUseCase,
    private val navigator: Navigator
) : ViewModel() {

    var state by mutableStateOf(StatsSubPageState())
        private set

    init {
        initImmidiateState()
        loadAbilities()
    }

    fun openAbilityDetails(abilityData: PokemonAbilityData) = viewModelScope.launch {
        navigator.navigate("pdx://ability/${abilityData.name}")
    }

    private fun initImmidiateState() {
        state = state.copy(
            baseStats = selectedVariety.stats.toSortedMap(Comparator.comparing { it.ordinal })
                .map { StatsSubPageState.StatDescription(it.key, it.value) },
            types = selectedVariety.types,
            archetype = selectedVariety.archetype,
        )
    }

    private fun loadAbilities() = viewModelScope.launch {
        val data = getPokemonAbilities.invoke(selectedVariety)
            .classify(StatsSubPageViewModel::class)

        state = when (data) {
            is Either.Left -> state // TODO handle error
            is Either.Right -> state.copy(
                abilities = data.value.map { list ->
                    list.mapLce { ability ->
                        PokemonAbilityData.from(ability)
                    }
                }
            )
        }
    }
}
