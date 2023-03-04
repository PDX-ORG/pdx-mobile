package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import arrow.core.continuations.either
import arrow.core.flatMap
import arrow.core.traverse
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonAbility
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.util.ofCurrentLocale
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.entity.ability.Ability
import io.lexadiky.pokeapi.entity.pokemon.Pokemon

class GetPokemonAbilitiesUseCase(
    private val client: PokeApiClient,
    private val getAbility: GetAbilityUseCase
) {

    suspend operator fun invoke(varietyDetails: PokemonDetails): Either<Error, List<PokemonAbility>> =
        listAbilities(varietyDetails).flatMap { abilitySlots ->
            abilitySlots.traverse { slot: Pokemon.AbilitySlot -> getAbility(slot.ability.name!!, slot.isHidden) }
                .mapLeft { Error }
        }
    
    private suspend fun listAbilities(details: PokemonDetails): Either<Error, List<Pokemon.AbilitySlot>> = Either.catch {
        client.pokemon.get(details.name).map { it.abilities }
            .getOrThrow()
    }.mapLeft { Error }

    object Error
}