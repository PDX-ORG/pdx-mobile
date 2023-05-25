package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonGameVersion
import io.github.lexadiky.pdx.domain.pokemon.util.ofCurrentLocale
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.library.locale.LocaleManager
import io.lexadiky.pokeapi.PokeApiClient

class GetPokemonGameVersionUseCase(
    private val pokeApiClient: PokeApiClient,
    private val localeManager: LocaleManager
) {

    suspend operator fun invoke(name: String): Either<GenericError, PokemonGameVersion> = either {
        val version = pokeApiClient.version.get(name)
            .bind { GenericError("can't load game version", it) }

        PokemonGameVersion(
            localeName = version.names.ofCurrentLocale(localeManager)
        )
    }
}
