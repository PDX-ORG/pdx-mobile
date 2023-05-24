package io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.library.core.error.GenericError

class FindPokemonPreviewUseCase(
    private val getAllPokemonPreviewsUseCase: GetAllPokemonPreviewsUseCase,
) {

    suspend operator fun invoke(pokemonId: String): Either<GenericError, PokemonPreview> =
        getAllPokemonPreviewsUseCase().map { list -> list.first { it.name == pokemonId } }
            .mapLeft { error -> GenericError("can't find pokemon preview", error) }
}
