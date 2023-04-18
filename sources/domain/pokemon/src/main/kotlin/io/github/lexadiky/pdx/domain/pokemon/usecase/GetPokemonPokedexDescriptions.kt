package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPokedexDescription
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.github.lexadiky.pdx.domain.pokemon.util.asPokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.util.normalizePokeApiText
import io.github.lexadiky.pdx.lib.locale.LocaleManager
import io.lexadiky.pokeapi.PokeApiClient

class GetPokemonPokedexDescriptions(
    private val pokeApiClient: PokeApiClient,
    private val getPokemonVersion: GetPokemonGameVersion,
    private val localeManager: LocaleManager
) {

    suspend operator fun invoke(name: String): Either<Error, List<PokemonPokedexDescription>> = either {
        val language = localeManager.settings.system.asPokemonLanguage()

        val species = pokeApiClient.pokemonSpecies.get(name).bind { Error }

        species.flavorTextEntries.filter { it.language.asLanguage() == language }
            .map { entry ->
                PokemonPokedexDescription(
                    text = entry.flavorText.normalizePokeApiText(),
                    gameVersion = getPokemonVersion(entry.version.name!!)
                        .mapLeft { Error }
                        .bind()
                )
            }
    }

    object Error
}