package io.github.lexadiky.pdx.domain.pokemon.usecase.move

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonMove
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.lib.core.error.GenericError
import io.github.lexadiky.pdx.lib.core.lce.DynamicLceList
import io.github.lexadiky.pdx.lib.core.lce.lceFlow
import io.github.lexadiky.pdx.lib.core.utils.asEither
import io.lexadiky.pokeapi.PokeApiClient

class GetPokemonMoves internal constructor(
    private val client: PokeApiClient,
    private val domainMapper: MoveDomainMapper
) {

    suspend operator fun invoke(
        pokemonDetails: PokemonSpeciesDetails
    ): Either<GenericError, DynamicLceList<GenericError, PokemonMove>> = either {
        val pokemon = client.pokemon.get(pokemonDetails.name).bind {
            GenericError("can't load pokemon details")
        }

        lceFlow(pokemon.moves) { moveSlot ->
            client.move.get(moveSlot)
                .asEither()
                .map { item -> domainMapper.map(item) }
                .mapLeft { GenericError("can't load pokemon moves") }
        }
    }
}
