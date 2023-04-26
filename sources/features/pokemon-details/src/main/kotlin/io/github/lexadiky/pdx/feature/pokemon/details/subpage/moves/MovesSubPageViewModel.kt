package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonMove
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.usecase.move.GetPokemonMoves
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSort
import io.github.lexadiky.pdx.lib.core.lce.Lce
import io.github.lexadiky.pdx.lib.core.lce.mapLce
import io.github.lexadiky.pdx.lib.errorhandler.classify
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovesSubPageViewModel(
    pokemonSpeciesDetails: PokemonSpeciesDetails,
    pokemonDetails: PokemonDetails,
    getPokemonMoves: GetPokemonMoves,
    private val navigator: Navigator
) : ViewModel() {

    var state by mutableStateOf(MovesSubPageState())
        private set

    init {
        viewModelScope.launch {
            val data = getPokemonMoves(pokemonSpeciesDetails)
                .classify(this)

            state = when (data) {
                is Either.Left -> state.copy(error = data.value)
                is Either.Right -> state.copy(moves = mapToData(data.value))
            }
        }
    }

    fun onMoveClicked(value: PokemonMoveData) = viewModelScope.launch {
        navigator.navigate("pdx://move/${value.name}?is_modal=true")
    }

    fun onTypeClicked(type: PokemonType) = viewModelScope.launch {
        navigator.navigate("pdx://type/${type.id}")
    }

    private fun mapToData(value: Flow<List<Lce<GetPokemonMoves.Error, PokemonMove>>>): Flow<List<Lce<*, PokemonMoveData>>> {
        return value.map { lces ->
            lces.mapLce { item ->
                PokemonMoveData(
                    name = item.name,
                    localeName = StringResource.from(item.localeName),
                    localeFlavourText = StringResource.from(item.localeFlavourText.orEmpty()),
                    type = item.type,
                    ppLabel = StringResource.from(item.pp?.toString().orEmpty()),
                    ppValue = item.pp ?: 0
                )
            }
        }
    }

    fun onSortUpdated(it: MoveSort) {
        state = state.copy(
            sortStrategy = it
        )
    }
}