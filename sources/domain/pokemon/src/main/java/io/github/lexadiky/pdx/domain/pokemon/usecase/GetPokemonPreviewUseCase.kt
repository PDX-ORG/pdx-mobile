package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.lib.fs.statist.StaticResourceProvider
import io.github.lexadiky.pdx.lib.fs.statist.provide

class GetPokemonPreviewUseCase internal constructor(
    private val resourceProvider: StaticResourceProvider,
) {

    suspend operator fun invoke(): Either<Error, List<PokemonPreview>> =
        resourceProvider.provide<List<PokemonPreview>>("bundle://discovery/pokemon.json").read()
            .mapLeft { Error }

    object Error
}