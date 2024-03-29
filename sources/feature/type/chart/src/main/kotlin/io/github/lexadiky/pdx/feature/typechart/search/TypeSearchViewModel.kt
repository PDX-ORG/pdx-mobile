package io.github.lexadiky.pdx.feature.typechart.search

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.nibbler.navigate
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonTypeDamageRelationsUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetAllPokemonPreviewsUseCase
import io.github.lexadiky.pdx.feature.typechart.entity.PokemonSuggester
import io.github.lexadiky.pdx.feature.typechart.entity.PokemonTypeSearchItem
import io.github.lexadiky.pdx.library.errorhandler.UIError
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from
import io.github.lexadiky.pdx.library.uikit.UikitDrawable
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.util.UikitStringFormatter
import kotlinx.coroutines.launch

internal class TypeSearchViewModel(
    private val getPokemonPreview: GetAllPokemonPreviewsUseCase,
    private val getPokemonDamageRelations: GetPokemonTypeDamageRelationsUseCase,
    private val suggester: PokemonSuggester,
    private val navigator: Navigator,
    private val context: Context
) : ViewModel() {

    var state by mutableStateOf(TypeSearchState())
        private set

    init {
        viewModelScope.launch {
            state = when (val data = getPokemonPreview()) {
                is Either.Left -> state.copy(error = UIError.generic())
                is Either.Right -> state.copy(allPokemon = data.value.toUi())
            }
        }
        viewModelScope.launch {
            state = when (val data = getPokemonDamageRelations()) {
                is Either.Left -> state.copy(error = UIError.generic())
                is Either.Right -> state.copy(
                    damageRelationsSubState = state.damageRelationsSubState.copy(
                        damageRelations = data.value
                    )
                )
            }
        }
    }

    fun hideError() {
        state = state.copy(error = null)
    }

    fun updateSearchQuery(text: String) {
        viewModelScope.launch {
            val suggestion = suggester.suggest(state.allPokemon, text)
            state = state.copy(
                searchQuery = text,
                suggestedPokemon = suggestion.hints,
            )
            state = if (suggestion.hints.isEmpty() || suggestion.hints.size == 1) {
                state.setSelectedPokemon(suggestion.mostLikely)
            } else {
                state.setSelectedPokemon(null)
            }
        }
    }

    fun selectHint(pokemon: PokemonTypeSearchItem) {
        updateSearchQuery(pokemon.name.render(context))
    }

    fun onTypeClicked(type: PokemonType) = viewModelScope.launch {
        navigator.navigate("pdx://type/${type.id}")
    }

    private fun List<PokemonPreview>.toUi(): List<PokemonTypeSearchItem> {
        return map { preview ->
            PokemonTypeSearchItem(
                name = StringResource.from(preview.localeName),
                searchQueryIndex = preview.searchIndex,
                image = preview.normalSprite?.let { ImageResource.from(it) }
                    ?: ImageResource.from(UikitDrawable.uikit_ic_pokeball),
                nationalId = UikitStringFormatter.nationalId(preview.nationalDexNumber),
                types = preview.types
            )
        }
    }
}
