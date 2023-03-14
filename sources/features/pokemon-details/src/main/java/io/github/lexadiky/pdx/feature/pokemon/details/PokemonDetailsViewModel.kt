package io.github.lexadiky.pdx.feature.pokemon.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonSpeciesDetailsUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.favorite.IsPokemonFavorite
import io.github.lexadiky.pdx.domain.pokemon.usecase.favorite.SaveFavoritePokemon
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.MarkPokemonSpeciesAsViewedUseCase
import io.github.lexadiky.pdx.feature.pokemon.details.usecase.GetAvailableDetailsSections
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.pdx.lib.navigation.Navigator
import kotlinx.coroutines.launch

internal class PokemonDetailsViewModel(
    private val pokemonId: String,
    private val navigator: Navigator,
    private val getPokemonDetails: GetPokemonSpeciesDetailsUseCase,
    private val getAvailableDetailsSections: GetAvailableDetailsSections,
    private val markPokemonSpeciesAsViewedUseCase: MarkPokemonSpeciesAsViewedUseCase,
    private val isPokemonFavorite: IsPokemonFavorite,
    private val saveFavoritePokemon: SaveFavoritePokemon
) : ViewModel() {

    var state: PokemonDetailsState by mutableStateOf(PokemonDetailsState(pokemonId))
        private set

    init {
        viewModelScope.launch {
            state = when (val data = getAvailableDetailsSections()) {
                is Either.Left -> state.copy(error = UIError.generic())
                is Either.Right -> state.copy(availableDetailsSections = data.value)
            }
            state = when (val details = getPokemonDetails(pokemonId)) {
                is Either.Left -> state.copy(error = UIError.generic())
                is Either.Right -> {
                    launch { markPokemonSpeciesAsViewedUseCase.invoke(details.value) }
                    createUpdatedState(details.value)
                }
            }
        }
    }

    private suspend fun createUpdatedState(details: PokemonSpeciesDetails): PokemonDetailsState {
        return state.copy(
            pokemonSpeciesDetails = details,
            selectedVariety = details.primaryVariety,
            isFavorite = isPokemonFavorite(details.primaryVariety)
        )
    }

    fun hideError() {
        state = state.copy(error = null)
    }

    fun openTypeDetails(type: PokemonType) = viewModelScope.launch {
        navigator.navigate("pdx://type/${type.id}")
    }

    fun selectVariety(varietyIndex: Int) = viewModelScope.launch {
        val selectedVariety = state.pokemonSpeciesDetails?.varieties?.get(varietyIndex) ?: return@launch
        state = state.copy(
            selectedVariety = selectedVariety,
            isFavorite = isPokemonFavorite(selectedVariety)
        )
    }

    fun openSprites() = viewModelScope.launch {
        navigator.navigate("pdx://pokemon/$pokemonId/${state.selectedVariety?.name}/sprites")
    }

    fun toggleFavorite() = viewModelScope.launch {
        val newIsFavorite = !state.isFavorite
        state = state.copy(isFavorite = newIsFavorite)

        val currentSpecies = state.pokemonSpeciesDetails ?: return@launch
        val currentVariety = state.selectedVariety ?: return@launch
        saveFavoritePokemon(currentSpecies, currentVariety, newIsFavorite)
    }
}
