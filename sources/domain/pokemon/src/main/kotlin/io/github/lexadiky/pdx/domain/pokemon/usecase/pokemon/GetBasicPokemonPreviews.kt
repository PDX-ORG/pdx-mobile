package io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.library.core.error.GenericError
import java.util.Random
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetBasicPokemonPreviews(
    private val getAll: GetAllPokemonPreviewsUseCase,
) {
    suspend operator fun invoke(): Either<GenericError, List<PokemonPreview>> = getAll()
        .map { pokemon ->
            val butch = pokemon.filter { it.nationalDexNumber in BASIC_IDS } + pokemon.shuffled(createRandomGenerator())
                .take(RANDOMIZED_SIZE)
            butch.shuffled(createRandomGenerator())
        }


    private fun createRandomGenerator() = Random(Clock.System.now().toLocalDateTime(TimeZone.UTC).year.toLong())

    companion object {

        private val BASIC_IDS = setOf(1, 4, 7, 25)
        private const val RANDOMIZED_SIZE = 10
    }
}