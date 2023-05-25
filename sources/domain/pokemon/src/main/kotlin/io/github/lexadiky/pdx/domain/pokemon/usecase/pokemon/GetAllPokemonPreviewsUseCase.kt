package io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.DiscoveryPokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.library.fs.statist.StaticResourceProvider
import io.github.lexadiky.pdx.library.fs.statist.provide
import io.github.lexadiky.pdx.library.locale.LocaleManager

class GetAllPokemonPreviewsUseCase internal constructor(
    private val resourceProvider: StaticResourceProvider,
    private val localeManager: LocaleManager
) {

    suspend operator fun invoke(): Either<GenericError, List<PokemonPreview>> =
        resourceProvider.provide<List<DiscoveryPokemonPreview>>("bundle://discovery/pokemon.json")
            .read()
            .map { list -> list.map { it.asDomain(localeManager) } }
            .mapLeft { error -> GenericError("can't load pokemon preview", error.reason) }
}
