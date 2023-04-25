package io.github.lexadiky.pdx.domain.pokemon.usecase.move

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonMove
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.asType
import io.github.lexadiky.pdx.domain.pokemon.util.asPokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.util.ofCurrentLocale
import io.github.lexadiky.pdx.lib.core.error.ErrorType
import io.github.lexadiky.pdx.lib.core.lce.Lce
import io.github.lexadiky.pdx.lib.core.lce.lceFlow
import io.github.lexadiky.pdx.lib.core.utils.asEither
import io.github.lexadiky.pdx.lib.core.utils.removeNewLines
import io.github.lexadiky.pdx.lib.locale.LocaleManager
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.entity.move.Move
import kotlinx.coroutines.flow.Flow

class GetPokemonMoves(
    private val client: PokeApiClient,
    private val localeManager: LocaleManager
) {

    suspend operator fun invoke(
        pokemonDetails: PokemonSpeciesDetails
    ): Either<Error, Flow<List<Lce<Error, PokemonMove>>>> = either {
        val pokemon = client.pokemon.get(pokemonDetails.name).bind { Error }

        lceFlow(pokemon.moves) { moveSlot ->
            client.move.get(moveSlot)
                .asEither()
                .map { item -> mapToDomain(item) }
                .mapLeft { Error }
        }
    }

    private fun mapToDomain(item: Move): PokemonMove {
        val flavorText = item.flavorTextEntries
            .lastOrNull { it.language.asLanguage() == localeManager.settings.system.asPokemonLanguage() }
            ?: item.flavorTextEntries.firstOrNull()

        return PokemonMove(
            name = item.name,
            localeName = item.names
                .ofCurrentLocale(localeManager),
            localeFlavourText = flavorText?.flavorText?.removeNewLines(),
            type = item.type.asType(),
            pp = item.pp
        )
    }

    object Error : ErrorType.Any, Throwable("can't load pokemon moves")
}
