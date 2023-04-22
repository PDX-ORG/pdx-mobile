package io.github.lexadiky.pdx.domain.pokemon.usecase.move

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonMove
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.lib.core.ErrorType
import io.github.lexadiky.pdx.lib.core.collection.replaced
import io.github.lexadiky.pdx.lib.core.lce.Lce
import io.github.lexadiky.pdx.lib.core.lce.lceFlow
import io.github.lexadiky.pdx.lib.core.utils.asEither
import io.github.lexadiky.pdx.lib.core.utils.asLce
import io.lexadiky.pokeapi.PokeApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class GetPokemonMoves(
    private val client: PokeApiClient
) {

    suspend operator fun invoke(
        pokemonDetails: PokemonSpeciesDetails
    ): Either<Error, Flow<List<Lce<Error, PokemonMove>>>> = either {
        val pokemon = client.pokemon.get(pokemonDetails.name).bind { Error }

        lceFlow(pokemon.moves) { moveSlot ->
            client.move.get(moveSlot)
                .asEither()
                .map { item -> PokemonMove(item.name) }
                .mapLeft { Error }
        }
    }

    object Error : ErrorType.Any, Throwable("can't load pokemon moves")
}
