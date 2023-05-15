package io.github.lexadiky.pdx.feature.pokemon.details.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonDetailsSection
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal class GetAvailableDetailsSections {

    suspend operator fun invoke(): Either<Error, ImmutableList<PokemonDetailsSection>> {
        return Either.Right(
            persistentListOf(
                PokemonDetailsSection.Stats,
                PokemonDetailsSection.Info,
                PokemonDetailsSection.Battle,
                PokemonDetailsSection.Evolution
            )
        )
    }

    object Error
}
