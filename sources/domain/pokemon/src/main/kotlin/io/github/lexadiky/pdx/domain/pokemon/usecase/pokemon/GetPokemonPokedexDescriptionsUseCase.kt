package io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon

import arrow.core.Either
import arrow.core.continuations.either
import arrow.core.traverse
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPokedexDescription
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonGameVersionUseCase
import io.github.lexadiky.pdx.domain.pokemon.util.asPokemonLanguage
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.library.core.lce.DynamicLceList
import io.github.lexadiky.pdx.library.core.lce.lceFlow
import io.github.lexadiky.pdx.library.core.utils.removeNewLines
import io.github.lexadiky.pdx.library.locale.LocaleManager
import io.lexadiky.pokeapi.PokeApiClient

class GetPokemonPokedexDescriptionsUseCase(
    private val pokeApiClient: PokeApiClient,
    private val getPokemonVersion: GetPokemonGameVersionUseCase,
    private val localeManager: LocaleManager,
) {

    suspend operator fun invoke(name: String): Either<GenericError, DynamicLceList<GenericError, PokemonPokedexDescription>> =
        either {
            val language = localeManager.settings.system.asPokemonLanguage()

            val species = pokeApiClient.pokemonSpecies.get(name)
                .bind { GenericError("can't get pokemon species", it) }

            val grouped = species.flavorTextEntries
                .filter { it.language.asLanguage() == language }
                .groupBy { it.flavorText }
                .entries
                .toList()

            lceFlow(grouped) { (text, descriptions) ->
                val versions = descriptions.traverse { entry ->
                    getPokemonVersion(entry.version.name!!)
                        .mapLeft { GenericError("can't load game version", it) }
                }

                versions.map { versionsUnpacked ->
                    PokemonPokedexDescription(
                        text = text.removeNewLines(),
                        gameVersions = versionsUnpacked
                    )
                }
            }
        }
}
