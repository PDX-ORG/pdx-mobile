package io.github.lexadiky.pdx.domain.pokemon.usecase.viewed

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.repository.ViewedPokemonRepository
import io.github.lexadiky.pdx.lib.core.error.GenericError

class MarkPokemonSpeciesAsViewedUseCase internal constructor(
    private val repository: ViewedPokemonRepository
) {

    suspend operator fun invoke(pokemon: PokemonSpeciesDetails): Either<GenericError, Unit> = repository
        .saveLatest(pokemon)
        .mapLeft { GenericError("can't mark species as viewed", it) }
}
