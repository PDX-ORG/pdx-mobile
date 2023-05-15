package io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats

import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.usecase.ability.GetPokemonAbilitiesUseCase
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonAbilityData
import io.github.lexadiky.pdx.lib.arc.ViewModelSocket
import io.github.lexadiky.pdx.lib.core.lce.mapLce
import io.github.lexadiky.pdx.lib.errorhandler.classify
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class StatsSubPageSocket(
    private val selectedVariety: PokemonDetails,
    private val getPokemonAbilities: GetPokemonAbilitiesUseCase,
    private val navigator: Navigator
) : ViewModelSocket<StatsSubPageState, StatsSubPageAction>(StatsSubPageState()) {

    init {
        initImmidiateState()
        loadAbilities()
    }

    override suspend fun onAction(action: StatsSubPageAction) = when (action) {
        is StatsSubPageAction.Navigate.AbilityDetails -> openAbilityDetails(action.data)
    }

    private fun openAbilityDetails(abilityData: PokemonAbilityData) {
        viewModelScope.launch {
            navigator.navigate("pdx://ability/${abilityData.name}")
        }
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
            .classify(StatsSubPageSocket::class)

        when (data) {
            is Either.Left -> Unit // TODO handle error
            is Either.Right -> {
                data.value.map { list ->
                    list.mapLce { ability ->
                        PokemonAbilityData.from(ability)
                    }
                }.collectLatest { list ->
                    state = state.copy(abilities = list)
                }
            }
        }
    }
}
