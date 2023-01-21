package io.github.lexadiky.pdx.feature.type.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.zip
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelation
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPreviewUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonTypeDamageRelations
import io.github.lexadiky.pdx.feature.type.details.entity.TypePokemonPreview
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import kotlinx.coroutines.launch
import kotlin.random.Random

internal class TypeDetailsViewModel(
    typeId: String,
    private val getPokemonTypeDamageRelations: GetPokemonTypeDamageRelations,
    private val getPokemonPreviewUseCase: GetPokemonPreviewUseCase
) : ViewModel() {

    var state by mutableStateOf(TypeDetailsState.from(typeId))
        private set

    init {
        viewModelScope.launch {
            val data = getPokemonTypeDamageRelations().mapLeft { UIError.default() }
                .zip(getPokemonPreviewUseCase().mapLeft { UIError.default() })

            state = when (val result = data) {
                is Either.Left -> state.copy(error = UIError.default())
                is Either.Right -> onStateLoaded(result.value.first, result.value.second)
            }
        }
    }

    private fun onStateLoaded(damageRelation: Map<PokemonType, PokemonTypeDamageRelation>, pokemon: List<PokemonPreview>): TypeDetailsState {
        return state.copy(
            damageRelations = damageRelation.getOrDefault(state.type, null),
            featuredPokemon = pokemon.filter { it.types.size == 1 && state.type in it.types }
                .shuffled(Random(FEATURED_POKEMON_BATCH_SEED))
                .take(FEATURED_POKEMON_BATCH_SIZE)
                .map { TypePokemonPreview.from(it) }
        )
    }

    fun hideError() {
        state = state.copy(error = null)
    }

    companion object {

        private const val FEATURED_POKEMON_BATCH_SEED = 42
        private const val FEATURED_POKEMON_BATCH_SIZE = 6
    }
}