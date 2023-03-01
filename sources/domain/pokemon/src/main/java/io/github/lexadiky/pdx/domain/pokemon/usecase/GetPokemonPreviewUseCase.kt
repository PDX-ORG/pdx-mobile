package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.DiscoveryPokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.lib.fs.statist.StaticResourceProvider
import io.github.lexadiky.pdx.lib.fs.statist.provide
import io.github.lexadiky.pdx.lib.locale.LocaleManager

class GetPokemonPreviewUseCase internal constructor(
    private val resourceProvider: StaticResourceProvider,
    private val localeManager: LocaleManager
) {

    suspend operator fun invoke(): Either<Error, List<PokemonPreview>> =
        resourceProvider.provide<List<DiscoveryPokemonPreview>>("bundle://discovery/pokemon.json").read()
            .map { list -> list.map { it.asDomain(localeManager) } }
            .mapLeft { Error }

    object Error
}
