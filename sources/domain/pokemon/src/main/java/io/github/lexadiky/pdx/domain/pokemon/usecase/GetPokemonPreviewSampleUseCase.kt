package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemonPreviewSampleUseCase(private val getPokemonPreview: GetPokemonPreviewUseCase) {

    suspend operator fun invoke(size: Int): Either<Error, List<PokemonPreview>> = withContext(Dispatchers.IO) {
        getPokemonPreview.invoke()
            .map { takeSample(it, size) }
            .mapLeft { Error }
    }

    private fun takeSample(pokemonPreviews: List<PokemonPreview>, size: Int): List<PokemonPreview> {
        val anchorType = PokemonType.values().random()
        return pokemonPreviews.filter { anchorType in it.types }
            .shuffled()
            .take(size)
    }

    object Error
}
