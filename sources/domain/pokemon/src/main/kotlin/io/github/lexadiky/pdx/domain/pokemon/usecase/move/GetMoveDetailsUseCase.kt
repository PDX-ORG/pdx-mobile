package io.github.lexadiky.pdx.domain.pokemon.usecase.move

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonMove
import io.github.lexadiky.pdx.domain.pokemon.mapper.MoveDomainMapper
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.library.core.utils.asEither
import io.lexadiky.pokeapi.PokeApiClient

class GetMoveDetailsUseCase internal constructor(
    private val client: PokeApiClient,
    private val domainMapper: MoveDomainMapper
) {

    suspend operator fun invoke(id: String): Either<GenericError, PokemonMove> =
        client.move.get(id).asEither()
            .map { move -> domainMapper.map(move) }
            .mapLeft { GenericError("can't fetch move with id $id", it) }
}
