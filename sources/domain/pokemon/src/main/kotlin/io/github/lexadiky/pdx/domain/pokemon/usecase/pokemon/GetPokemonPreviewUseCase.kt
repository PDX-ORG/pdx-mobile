package io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon

import arrow.core.Either
import arrow.core.flatMap
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.lib.core.error.GenericError

class GetPokemonPreviewUseCase internal constructor(
    private val getAllPokemonPreviewsUseCase: GetAllPokemonPreviewsUseCase
) {

    suspend operator fun invoke(speciesId: String): Either<GenericError, PokemonPreview> =
        getAllPokemonPreviewsUseCase().flatMap { previews ->
            previews.firstOrNull { it.name == speciesId }
                ?.let { Either.Right(it) }
                ?: Either.Left(GenericError.originate("can't find preview for speciesId: $speciesId"))
        }
}
