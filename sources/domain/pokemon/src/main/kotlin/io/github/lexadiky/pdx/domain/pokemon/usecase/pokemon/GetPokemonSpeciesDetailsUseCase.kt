package io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon

import arrow.core.Either
import arrow.core.continuations.either
import arrow.core.identity
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.UseRomajiLocaleFlag
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.github.lexadiky.pdx.domain.pokemon.util.asPokemonLanguage
import io.github.lexadiky.pdx.lib.core.error.GenericError
import io.github.lexadiky.pdx.lib.locale.LocaleManager
import io.lexadiky.pokeapi.PokeApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemonSpeciesDetailsUseCase(
    private val pokeApiClient: PokeApiClient,
    private val getPokemonVarietyDetails: GetPokemonVarietyDetailsUseCase,
    private val localeManager: LocaleManager
) {

    suspend operator fun invoke(id: String): Either<GenericError, PokemonSpeciesDetails> = either {
        withContext(Dispatchers.IO) {
            val species = pokeApiClient.pokemonSpecies.get(name = id).bind(::identity)
            val defaultVariety = species.varieties.first { it.isDefault }

            val names = species.names.associate { name -> name.language.asLanguage() to name }
            val localeName = if (localeManager.settings.has(UseRomajiLocaleFlag)) {
                names[PokemonLanguage.JA_ROOMAJI]?.name
            } else {
                names[localeManager.settings.system.asPokemonLanguage()]?.name
            } ?: names.values.first().name

            PokemonSpeciesDetails(
                name = species.name,
                localeName = localeName,
                primaryVariety = getPokemonVarietyDetails.invoke(defaultVariety.pokemon.name!!)
                    .bind(),
                // TODO not really national pokedex
                nationalDexNumber = species.pokedexNumbers.first().entryNumber,
                isLegendary = species.isLegendary,
                isMythical = species.isMythical,
                availableVarietiesCount = species.varieties.size,
            )
        }
    }.mapLeft { error ->
        GenericError("can't load pokemon species", error)
    }
}
