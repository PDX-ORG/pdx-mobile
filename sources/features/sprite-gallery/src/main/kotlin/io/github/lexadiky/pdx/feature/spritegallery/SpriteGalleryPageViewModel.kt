package io.github.lexadiky.pdx.feature.spritegallery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonVarietyDetails
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.coroutines.launch

internal class SpriteGalleryPageViewModel(
    private val speciesId: String,
    private val varietyId: String,
    private val getPokemonVarietyDetails: GetPokemonVarietyDetails,
) : ViewModel() {

    var state by mutableStateOf(PokemonGalleryState())
        private set

    init {
        viewModelScope.launch {
            state = when (val data = getPokemonVarietyDetails(speciesId, varietyId)) {
                is Either.Left -> state.copy(error = UIError.generic())
                is Either.Right -> state.copy(
                    items = data.value.sprites.all.map {
                        StringResource.from("<UNKNOWN>") to ImageResource.from(it)
                    }
                )
            }
        }
    }

    fun hideError() {
        state = state.copy(error = null)
    }
}
