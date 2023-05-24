package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves

import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonMove
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.usecase.move.GetPokemonMovesUseCase
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSort
import io.github.lexadiky.pdx.library.arc.ViewModelSocket
import io.github.lexadiky.pdx.library.core.lce.DynamicLceList
import io.github.lexadiky.pdx.library.core.lce.mapLce
import io.github.lexadiky.pdx.library.errorhandler.classify
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class MovesSubPageSocket(
    pokemonSpeciesDetails: PokemonSpeciesDetails,
    getPokemonMoves: GetPokemonMovesUseCase,
    private val navigator: Navigator
) : ViewModelSocket<MovesSubPageState, MovesSubPageAction>(MovesSubPageState()) {

    override suspend fun onAction(action: MovesSubPageAction) = when (action) {
        is MovesSubPageAction.QueryUpdated -> onQueryUpdated(action.query)
        is MovesSubPageAction.SortUpdated -> onSortUpdated(action.sort)
        is MovesSubPageAction.Navigate.MoveDetails -> onMoveClicked(action.value)
        is MovesSubPageAction.Navigate.TypeDetails -> onTypeClicked(action.type)
    }

    init {
        viewModelScope.launch {
            val data = getPokemonMoves(pokemonSpeciesDetails)
                .classify(MovesSubPageSocket::class)

            when (data) {
                is Either.Left -> state = state.copy(error = data.value)
                is Either.Right -> {
                    mapToData(data.value).collectLatest { latest ->
                        state = state.copy(movesRaw = latest)
                    }
                }
            }
        }
    }

    fun onMoveClicked(value: PokemonMoveData) {
        viewModelScope.launch {
            navigator.navigate("pdx://move/${value.name}?is_modal=true")
        }
    }

    fun onTypeClicked(type: PokemonType) {
        viewModelScope.launch {
            navigator.navigate("pdx://type/${type.id}")
        }
    }

    fun onQueryUpdated(query: String) {
        state = state.copy(filter = state.filter.copy(query = query))
    }

    fun onSortUpdated(sort: MoveSort) {
        state = state.copy(sortStrategy = sort)
    }

    private fun mapToData(value: DynamicLceList<*, PokemonMove>): DynamicLceList<*, PokemonMoveData> {
        return value.map { lces ->
            lces.mapLce { item ->
                PokemonMoveData(
                    name = item.name,
                    localeName = StringResource.from(item.localeName),
                    localeEffectText = StringResource.from(item.localeShortEffect.orEmpty()),
                    type = item.type,
                    pp = item.pp,
                    power = item.power,
                    accuracy = item.accuracy,
                    ftsIndex = item.ftsIndex
                )
            }
        }
    }
}
