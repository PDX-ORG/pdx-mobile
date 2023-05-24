package io.github.lexadiky.pdx.domain.pokemon.usecase.ability

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonAbility
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.library.core.lce.DynamicLceList
import io.github.lexadiky.pdx.library.core.lce.lceFlow
import io.github.lexadiky.pdx.library.core.utils.asEither
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.entity.pokemon.Pokemon

class GetPokemonAbilitiesUseCase(
    private val client: PokeApiClient,
    private val getAbility: GetAbilityUseCase,
) {

    suspend operator fun invoke(varietyDetails: PokemonDetails):Either<GenericError, DynamicLceList<GenericError, PokemonAbility>> = either {
        val abilities = listAbilities(varietyDetails).bind()
        lceFlow(abilities) { slot ->
            getAbility(slot.ability.name!!, slot.isHidden)
                .mapLeft { GenericError("can't load ability details", it) }
        }
    }

    private suspend fun listAbilities(details: PokemonDetails): Either<GenericError, List<Pokemon.AbilitySlot>> =
        client.pokemon.get(details.name).map { it.abilities }
            .asEither()
            .mapLeft { GenericError("can't load pokemon", it) }
}
