package io.github.lexadiky.pdx.domain.pokemon.repository

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.FindPokemonPreviewUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.GetLatestViewedPokemonUseCase
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.lib.microdata.MicrodataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ViewedPokemonRepository(
    microdataManager: MicrodataManager,
    private val  findPokemonPreviewUseCase: FindPokemonPreviewUseCase
) {
    private val microdata = microdataManager.acquire(this, "viewed_pokemon")
    private val visited = microdata.strings("viewed_time_id")

    suspend fun saveLatest(pokemon: PokemonSpeciesDetails): Either<GenericError, Unit> = withContext(Dispatchers.IO) {
        Either.catch {
            val filteredVisited = visited.get().orEmpty().filter { pokemon.name !in it }.toSet()
            visited.set(
                (filteredVisited + "${System.currentTimeMillis()}$VALUE_SEPARATOR${pokemon.name}")
                    .sortedDescending()
                    .take(SAMPLE_SIZE)
                    .toSet()
            )
        }.mapLeft {
            GenericError("can't save latest viewed pokemon", it)
        }
    }

    suspend fun latest(size: Int) = either {
        val latestVisitedSample = Either.catch {
            visited.get().orEmpty().sortedDescending()
                .take(size)
                .map { it.split(VALUE_SEPARATOR).last() }
        }.bind()

        latestVisitedSample.map {
            findPokemonPreviewUseCase(it)
                .mapLeft { Exception("can't load pokemon details") }
                .bind()
        }
    }.mapLeft { error ->
        BLogger.tag("GetLatestViewedPokemonUseCase")
            .error("can't get latest visited pokemon", error)
        GetLatestViewedPokemonUseCase.Error
    }

    object Error

    companion object {

        private const val VALUE_SEPARATOR = ":"
        private const val SAMPLE_SIZE = 10
    }
}
