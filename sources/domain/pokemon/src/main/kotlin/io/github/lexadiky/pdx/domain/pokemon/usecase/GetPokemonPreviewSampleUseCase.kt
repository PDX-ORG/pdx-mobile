package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.DiscoveryPokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlin.random.Random

class GetPokemonPreviewSampleUseCase(private val getPokemonPreview: GetPokemonPreviewUseCase) {

    private val randomGenerator = Random(Clock.System.now().epochSeconds / (60 * 60 * 24))

    suspend operator fun invoke(size: Int): Either<Error, List<PokemonPreview>> = withContext(Dispatchers.IO) {
        getPokemonPreview.invoke()
            .map { takeSample(it, size) }
            .mapLeft { Error }
    }

    private fun takeSample(pokemonPreviews: List<PokemonPreview>, size: Int): List<PokemonPreview> {
        val anchorType = PokemonType.values().random(randomGenerator)
        return pokemonPreviews.filter { anchorType in it.types }
            .shuffled(randomGenerator)
            .take(size)
    }

    object Error
}
