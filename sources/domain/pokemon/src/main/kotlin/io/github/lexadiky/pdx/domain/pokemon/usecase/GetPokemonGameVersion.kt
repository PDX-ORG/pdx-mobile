package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonGameVersion
import io.github.lexadiky.pdx.domain.pokemon.util.ofCurrentLocale
import io.github.lexadiky.pdx.lib.locale.LocaleManager
import io.lexadiky.pokeapi.PokeApiClient

class GetPokemonGameVersion(
    private val pokeApiClient: PokeApiClient,
    private val localeManager: LocaleManager
) {

    suspend operator fun invoke(name: String): Either<Error, PokemonGameVersion> = either {
        val version = pokeApiClient.version.get(name).bind { Error }

        PokemonGameVersion(
            localeName = version.names.ofCurrentLocale(localeManager)
        )
    }

    object Error
}
