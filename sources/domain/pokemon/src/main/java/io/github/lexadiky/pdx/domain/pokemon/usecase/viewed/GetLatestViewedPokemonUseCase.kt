package io.github.lexadiky.pdx.domain.pokemon.usecase.viewed

import arrow.core.Either
import arrow.core.computations.ResultEffect.bind
import arrow.core.continuations.either
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.pdx.domain.pokemon.usecase.FindPokemonPreviewUseCase
import io.github.lexadiky.pdx.lib.fs.FsManager
import java.lang.Exception

class GetLatestViewedPokemonUseCase(
    fsManager: FsManager,
    private val findPokemonPreviewUseCase: FindPokemonPreviewUseCase
) {
    private var visited by fsManager.atomic("viewed-pokemon")
        .stringSet("viewed-time-id", emptySet())

    suspend operator fun invoke(size: Int) = either {
        val latestVisitedSample = Either.catch {
            visited.sorted()
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
        Error
    }

    object Error
}
