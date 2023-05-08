package io.github.lexadiky.pdx.feature.pokemon.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.FindPokemonPreviewUseCase
import io.github.lexadiky.pdx.lib.errorhandler.classify
import io.github.lexadiky.pdx.lib.resources.color.ColorResource
import kotlinx.coroutines.launch

internal data class PokemonDetailsStyle(val color: ColorResource? = null)

internal class PokemonDetailsStyleFastFetchViewModel(
    private val pokemonId: String,
    private val findPokemonPreview: FindPokemonPreviewUseCase
) : ViewModel() {

    var state by mutableStateOf(PokemonDetailsStyle())
        private set

    init {
        viewModelScope.launch {
            when (val data = findPokemonPreview.invoke(pokemonId).classify(PokemonDetailsStyleFastFetchViewModel::class)) {
                is Either.Left -> Unit // TODO show error
                is Either.Right -> state = state.copy(color = data.value.types.first().assets.color)
            }
        }
    }
}
