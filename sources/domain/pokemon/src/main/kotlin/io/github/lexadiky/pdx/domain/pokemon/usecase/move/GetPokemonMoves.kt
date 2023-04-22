package io.github.lexadiky.pdx.domain.pokemon.usecase.move

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonMove
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.lib.core.ErrorType
import io.github.lexadiky.pdx.lib.core.collection.replaced
import io.github.lexadiky.pdx.lib.core.lce.Lce
import io.lexadiky.pokeapi.PokeApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
        val mutex = Mutex()
        var readyBuffer: List<Lce<Error, PokemonMove>> = List(pokemon.moves.size) { Lce.Loading }

        flow {
            emit(readyBuffer)
            pokemon.moves.forEachIndexed { index, moveSlot ->
                val detailsResult = client.move.get(moveSlot)
                if (detailsResult.isFailure) {
                    mutex.withLock {
                        readyBuffer = readyBuffer.replaced(index, Lce.Error(Error))
                        emit(readyBuffer)
                    }
                } else {
                    val details = detailsResult.getOrThrow()
                    mutex.withLock {
                        readyBuffer =
                            readyBuffer.replaced(index, Lce.Content(PokemonMove(details.name)))
                        emit(readyBuffer)
                    }
                }
            }
        }
    }

    object Error : ErrorType.Any, Throwable("can't load pokemon moves")
}