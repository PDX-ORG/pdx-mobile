package io.github.lexadiky.pdx.domain.pokemon.usecase.viewed

import arrow.core.Either
import arrow.core.computations.ResultEffect.bind
import arrow.core.continuations.either
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.pdx.domain.pokemon.repository.ViewedPokemonRepository
import io.github.lexadiky.pdx.domain.pokemon.usecase.FindPokemonPreviewUseCase
import io.github.lexadiky.pdx.lib.fs.FsManager
import io.github.lexadiky.pdx.lib.microdata.MicrodataManager
import java.lang.Exception

class GetLatestViewedPokemonUseCase internal constructor(
    private val repository: ViewedPokemonRepository
) {
    suspend operator fun invoke(size: Int) = repository
        .latest(size)
        .mapLeft { Error }

    object Error
}
