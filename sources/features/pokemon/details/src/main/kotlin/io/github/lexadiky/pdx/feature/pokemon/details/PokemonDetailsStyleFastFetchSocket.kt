package io.github.lexadiky.pdx.feature.pokemon.details

import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.FindPokemonPreviewUseCase
import io.github.lexadiky.pdx.library.arc.ViewModelSocket
import io.github.lexadiky.pdx.library.errorhandler.classify
import io.github.lexadiky.pdx.lib.resources.color.ColorResource
import kotlinx.coroutines.launch

internal data class PokemonDetailsStyle(val color: ColorResource? = null)

internal class PokemonDetailsStyleFastFetchSocket(
    private val pokemonId: String,
    private val findPokemonPreview: FindPokemonPreviewUseCase
) : ViewModelSocket<PokemonDetailsStyle, Unit>(PokemonDetailsStyle()) {

    init {
        viewModelScope.launch {
            when (val data = findPokemonPreview.invoke(pokemonId).classify(PokemonDetailsStyleFastFetchSocket::class)) {
                is Either.Left -> Unit // ignore do nothing
                is Either.Right -> state = state.copy(color = data.value.types.first().assets.color)
            }
        }
    }

    override suspend fun onAction(action: Unit) = Unit
}
