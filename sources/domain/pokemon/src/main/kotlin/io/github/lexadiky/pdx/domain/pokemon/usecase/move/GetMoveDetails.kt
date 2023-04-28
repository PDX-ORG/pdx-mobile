package io.github.lexadiky.pdx.domain.pokemon.usecase.move

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonMove
import io.github.lexadiky.pdx.lib.core.error.GenericError
import io.lexadiky.pokeapi.PokeApiClient

class GetMoveDetails internal constructor(
    private val client: PokeApiClient,
    private val domainMapper: MoveDomainMapper
) {

    suspend operator fun invoke(id: String) : Either<GenericError, PokemonMove> = either {
        val move = client.move.get(id).bind {
            GenericError("can't fetch move with id $id")
        }

        domainMapper.map(move)
    }
}
