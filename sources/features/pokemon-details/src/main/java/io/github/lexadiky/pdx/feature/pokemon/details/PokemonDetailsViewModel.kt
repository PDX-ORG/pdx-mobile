package io.github.lexadiky.pdx.feature.pokemon.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonSpeciesDetailsUseCase
import io.github.lexadiky.pdx.feature.pokemon.details.usecase.GetAvailableDetailsSections
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.pdx.lib.navigation.Navigator
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.*
import kotlinx.coroutines.launch

internal class PokemonDetailsViewModel(
    private val pokemonId: String,
    private val navigator: Navigator,
    private val getPokemonDetails: GetPokemonSpeciesDetailsUseCase,
    private val getAvailableDetailsSections: GetAvailableDetailsSections
) : ViewModel() {

    var state: PokemonDetailsState by mutableStateOf(PokemonDetailsState(pokemonId))
        private set

    init {
        viewModelScope.launch {
            state = when (val data = getAvailableDetailsSections()) {
                is Either.Left -> state.copy(error = UIError.default())
                is Either.Right -> state.copy(availableDetailsSections = data.value)
            }
            state = when (val details = getPokemonDetails(pokemonId)) {
                is Either.Left -> state.copy(error = UIError.default())
                is Either.Right -> createUpdatedState(details.value)
            }
        }
    }

    private fun createUpdatedState(details: PokemonSpeciesDetails): PokemonDetailsState {
        return state.copy(
            pokemonSpeciesDetails = details,
            selectedVariety = details.primaryVariety
        )
    }

    fun hideError() {
        state = state.copy(error = null)
    }

    fun openTypeDetails(type: PokemonType) = viewModelScope.launch {
        navigator.navigate("pdx://type/${type.id}")
    }

    fun selectVariety(varietyIndex: Int) {
        state = state.copy(
            selectedVariety = state.pokemonSpeciesDetails?.varieties?.get(varietyIndex)
        )
    }
}