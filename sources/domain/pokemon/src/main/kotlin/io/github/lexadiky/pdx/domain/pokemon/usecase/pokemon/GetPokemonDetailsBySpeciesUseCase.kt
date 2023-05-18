package io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.lib.core.error.GenericError
import io.github.lexadiky.pdx.lib.core.lce.DynamicLceList
import io.github.lexadiky.pdx.lib.core.lce.lceFlow
import io.lexadiky.pokeapi.PokeApiClient

class GetPokemonDetailsBySpeciesUseCase(
    private val client: PokeApiClient,
    private val getPokemonVarietyDetails: GetPokemonVarietyDetailsUseCase
) {

    suspend operator fun invoke(id: String): Either<GenericError, DynamicLceList<GenericError, PokemonDetails>> = either {
        val species = client.pokemonSpecies.get(id)
            .bind { GenericError("can't get pokemon species", it) }

        lceFlow(species.varieties) { slot ->
            getPokemonVarietyDetails.invoke(slot.pokemon.name!!)
        }
    }
}
