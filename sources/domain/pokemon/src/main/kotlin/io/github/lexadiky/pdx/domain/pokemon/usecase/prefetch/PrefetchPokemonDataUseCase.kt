package io.github.lexadiky.pdx.domain.pokemon.usecase.prefetch

import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.akore.blogger.verbose
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.accessor.GenericAccessor
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrefetchPokemonDataUseCase(private val pokeApiClient: PokeApiClient) {
    private val logger = BLogger.tag("PrefetchPokemonData")

    suspend operator fun invoke() {
        try {
            prefetch(pokeApiClient.version)
            prefetch(pokeApiClient.pokemonColor)
            prefetch(pokeApiClient.language)
            prefetch(pokeApiClient.type)
            prefetch(pokeApiClient.stat)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            logger.error("can't prefetch data", e)
        }
    }

    private suspend fun <T> prefetch(accessor: GenericAccessor<T>) = withContext(Dispatchers.IO + Job()) {
        logger.verbose("prefetching $accessor started")
        accessor.all().getOrThrow().forEach { elementRef ->
            launch { accessor.get(elementRef).getOrThrow() }
        }
        logger.verbose("prefetching $accessor done")
    }
}
