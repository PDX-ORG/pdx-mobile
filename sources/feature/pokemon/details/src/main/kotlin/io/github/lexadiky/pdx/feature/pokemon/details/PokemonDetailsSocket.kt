package io.github.lexadiky.pdx.feature.pokemon.details

import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.nibbler.navigate
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.usecase.favorite.IsPokemonFavoriteUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.favorite.SaveFavoritePokemonUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetPokemonDetailsBySpeciesUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetPokemonSpeciesDetailsUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.MarkPokemonSpeciesAsViewedUseCase
import io.github.lexadiky.pdx.feature.pokemon.details.toggle.SpritesViewerFeatureToggle
import io.github.lexadiky.pdx.feature.pokemon.details.usecase.GetAvailableDetailsSections
import io.github.lexadiky.pdx.library.featuretoggle.FeatureToggleManager
import io.github.lexadiky.pdx.library.arc.ViewModelSocket
import io.github.lexadiky.pdx.library.core.lce.contentOrNull
import io.github.lexadiky.pdx.library.errorhandler.UIError
import io.github.lexadiky.pdx.library.errorhandler.classify
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class PokemonDetailsSocket(
    pokemonId: String,
    private val navigator: Navigator,
    private val getPokemonDetails: GetPokemonSpeciesDetailsUseCase,
    private val getAvailableDetailsSections: GetAvailableDetailsSections,
    private val markPokemonSpeciesAsViewedUseCase: MarkPokemonSpeciesAsViewedUseCase,
    private val isPokemonFavorite: IsPokemonFavoriteUseCase,
    private val saveFavoritePokemon: SaveFavoritePokemonUseCase,
    featureToggleManager: FeatureToggleManager,
    private val getPokemonDetailsBySpecies: GetPokemonDetailsBySpeciesUseCase
) : ViewModelSocket<PokemonDetailsState, PokemonDetailsAction>(PokemonDetailsState(pokemonId)) {

    init {
        state = state.copy(
            isSpritesViewerEnabled = featureToggleManager.resolve(SpritesViewerFeatureToggle)
        )

        viewModelScope.launch {
            state = when (val data = getAvailableDetailsSections()) {
                is Either.Left -> state.copy(error = UIError.generic())
                is Either.Right -> state.copy(availableDetailsSections = data.value)
            }

            when (val data = getPokemonDetailsBySpecies(pokemonId).classify(PokemonDetailsSocket::class)) {
                is Either.Left -> state = state.copy(error = data.value)
                is Either.Right -> {
                    data.value.collectLatest { lces ->
                        state = state.copy(varieties = lces)
                    }
                }
            }

            state = when (val details =
                getPokemonDetails(pokemonId).classify(PokemonDetailsSocket::class)) {
                is Either.Left -> state.copy(error = details.value)
                is Either.Right -> {
                    launch { markPokemonSpeciesAsViewedUseCase.invoke(details.value) }
                    createUpdatedState(details.value)
                }
            }

            selectVariety(0)
        }
    }

    override suspend fun onAction(action: PokemonDetailsAction) = when (action) {
        PokemonDetailsAction.Navigate.Sprites -> openSprites()
        PokemonDetailsAction.ToggleFavorite -> toggleFavorite()
        is PokemonDetailsAction.SelectVariety -> selectVariety(action.index)
        PokemonDetailsAction.HideError -> hideError()
        is PokemonDetailsAction.OpenTypeDetails -> openTypeDetails(action.type)
    }

    private fun hideError() {
        state = state.copy(error = null)
    }

    private fun openTypeDetails(type: PokemonType) {
        viewModelScope.launch {
            navigator.navigate("pdx://type/${type.id}")
        }
    }

    private fun selectVariety(varietyIndex: Int) {
        viewModelScope.launch {
            state = state.copy(selectedVariety = state.varieties[varietyIndex])
        }
    }

    private fun openSprites() {
        viewModelScope.launch {
            val name = state.selectedVariety.contentOrNull()?.name
            navigator.navigate("pdx://pokemon/${state.id}/$name/sprites")
        }
    }

    private fun toggleFavorite() {
        viewModelScope.launch {
            val newIsFavorite = !state.isFavorite
            state = state.copy(isFavorite = newIsFavorite)

            val currentSpecies = state.pokemonSpeciesDetails ?: return@launch
            val currentVariety = state.selectedVariety.contentOrNull() ?: return@launch
            saveFavoritePokemon(currentSpecies, currentVariety, newIsFavorite)
        }
    }

    private suspend fun createUpdatedState(details: PokemonSpeciesDetails): PokemonDetailsState {
        return state.copy(
            pokemonSpeciesDetails = details,
            isFavorite = isPokemonFavorite(details.primaryVariety)
        )
    }
}
