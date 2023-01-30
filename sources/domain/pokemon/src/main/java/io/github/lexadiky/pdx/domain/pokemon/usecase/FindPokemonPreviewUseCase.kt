package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview

class FindPokemonPreviewUseCase(private val getPokemonPreviewUseCase: GetPokemonPreviewUseCase) {

    suspend operator fun invoke(pokemonId: String): Either<Error, PokemonPreview> =
        getPokemonPreviewUseCase().map { it.first { it.name == pokemonId } }
            .mapLeft { Error }

    object Error
}
