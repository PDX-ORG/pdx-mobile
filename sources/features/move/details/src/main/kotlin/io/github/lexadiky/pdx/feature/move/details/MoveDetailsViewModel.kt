package io.github.lexadiky.pdx.feature.move.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonMove
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.usecase.move.GetMoveDetails
import io.github.lexadiky.pdx.lib.errorhandler.classify
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.coroutines.launch

internal class MoveDetailsViewModel(
    private val moveId: String,
    private val getMoveDetails: GetMoveDetails,
    private val navigator: Navigator
) : ViewModel() {

    var state by mutableStateOf(MoveDetailsState())
        private set

    init {
        viewModelScope.launch {
            state = when (val data = getMoveDetails(moveId).classify(this@MoveDetailsViewModel)) {
                is Either.Left -> state.copy(error = data.value)
                is Either.Right -> moveDetailsState(data.value)
            }
        }
    }

    private fun moveDetailsState(move: PokemonMove) = state.copy(
        localeName = StringResource.from(move.localeName),
        localeFlavourText = move.localeFlavourText?.let { StringResource.from(it) },
        type = move.type,
        pp = move.pp
    )

    fun hideError() {
        state = state.copy(error = null)
    }

    fun onTypeClicked(type: PokemonType) = viewModelScope.launch {
        navigator.navigate("pdx://type/${type.id}")
    }
}