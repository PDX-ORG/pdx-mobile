package io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.DiscoveryPokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.library.core.cache.MemoryCache
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.library.core.resource.ResourceReader
import io.github.lexadiky.pdx.library.core.resource.read
import io.github.lexadiky.pdx.library.locale.LocaleManager

class GetAllPokemonPreviewsUseCase internal constructor(
    private val resourceReader: ResourceReader,
    private val localeManager: LocaleManager,
) {
    private val cache = MemoryCache.fresh<List<PokemonPreview>>()

    suspend operator fun invoke(): Either<GenericError, List<PokemonPreview>> = cache.update {
        resourceReader.read<List<DiscoveryPokemonPreview>>("discovery/pokemon.json")
            .map { list -> list.map { it.asDomain(localeManager) } }
            .mapLeft { error -> GenericError("can't load pokemon preview", error) }
    }
}
