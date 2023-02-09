package io.github.lexadiky.pdx.domain.pokemon.usecase.viewed

import arrow.core.Either
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.lib.fs.FsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarkPokemonSpeciesAsViewedUseCase(
    fsManager: FsManager
) {
    private var visited by fsManager.atomic("viewed-pokemon")
        .stringSet("viewed-time-id", emptySet())

    suspend operator fun invoke(pokemon: PokemonSpeciesDetails) = withContext(Dispatchers.IO) {
        Either.catch {
            visited = (visited + "${System.currentTimeMillis()}:${pokemon.name}")
                .sorted()
                .take(10)
                .toSet()
        }.mapLeft { error ->
            BLogger.tag("MarkPokemonSpeciesAsViewedUseCase")
                .error("can't mark pokemon as viewed", error)
            Error
        }
    }

    object Error
}
