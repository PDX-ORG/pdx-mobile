package io.github.lexadiky.pdx.feature.whois

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPreviewUseCase
import io.github.lexadiky.pdx.feature.whois.entity.WhoIsPokemonVariant
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class WhoIsViewModel(
    private val getPokemonPreviewUseCase: GetPokemonPreviewUseCase
) : ViewModel() {

    var state by mutableStateOf(WhoIsState())
        private set

    init {
        viewModelScope.launch {
            state = when (val data = getPokemonPreviewUseCase()) {
                is Either.Left -> state.copy(error = UIError.default())
                is Either.Right -> state.copy(allPokemon = data.value.toVariants())
            }.reshuffleNew()
        }
    }

    fun hideError() {
        state = state.copy(error = null)
    }

    fun onAnswer(answer: WhoIsPokemonVariant) {
        if (!state.masked) {
            return
        }
        viewModelScope.launch {
            state = state.copy(masked = false)
            delay(GUESS_RESULT_LEN)
            state = state
                .copy(masked = true)
                .reshuffleNew()
        }
    }

    private fun List<PokemonPreview>.toVariants(): List<WhoIsPokemonVariant> {
        return filter { it.normalSprite != null }.map { preview ->
            WhoIsPokemonVariant(
                image = ImageResource.from(preview.normalSprite!!),
                name = StringResource.from(preview.localNames[PokemonLanguage.ENGLISH]!!),
                id = preview.name
            )
        }
    }

    companion object {

        private val GUESS_RESULT_LEN = 5.seconds
    }
}