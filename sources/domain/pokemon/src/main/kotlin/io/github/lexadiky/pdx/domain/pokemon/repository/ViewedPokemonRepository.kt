package io.github.lexadiky.pdx.domain.pokemon.repository

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.usecase.FindPokemonPreviewUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.GetLatestViewedPokemonUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.MarkPokemonSpeciesAsViewedUseCase
import io.github.lexadiky.pdx.lib.microdata.MicrodataManager
import java.lang.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ViewedPokemonRepository(
    microdataManager: MicrodataManager,
    private val  findPokemonPreviewUseCase: FindPokemonPreviewUseCase
) {
    private val microdata = microdataManager.acquire(this, "viewed_pokemon")
    private var visited = microdata.strings("viewed_time_id")

    suspend fun saveLatest(pokemon: PokemonSpeciesDetails) = withContext(Dispatchers.IO) {
        Either.catch {
            val filteredVisited = visited.get().orEmpty().filter { pokemon.name !in it }.toSet()
            visited.set(
                (filteredVisited + "${System.currentTimeMillis()}:${pokemon.name}")
                    .sortedDescending()
                    .take(10)
                    .toSet()
            )
        }.mapLeft { error ->
            BLogger.tag("MarkPokemonSpeciesAsViewedUseCase")
                .error("can't mark pokemon as viewed", error)
            MarkPokemonSpeciesAsViewedUseCase.Error
        }
    }

    suspend fun latest(size: Int) = either {
        val latestVisitedSample = Either.catch {
            visited.get().orEmpty().sortedDescending()
                .take(size)
                .map { it.split(":").last() }
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
}