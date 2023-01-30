package io.github.lexadiky.pdx.feature.pokemon.details.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonDetailsSection
import kotlinx.coroutines.delay

internal class GetAvailableDetailsSections {

    suspend operator fun invoke(): Either<Error, List<PokemonDetailsSection>> {
        return Either.Right(listOf(
            PokemonDetailsSection.Stats,
            PokemonDetailsSection.Info,
            PokemonDetailsSection.Battle,
            PokemonDetailsSection.Evolution
        ))
    }

    object Error
}