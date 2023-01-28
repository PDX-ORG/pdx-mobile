package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSprites
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.asType
import io.lexadiky.pokeapi.PokeApiClient

class GetPokemonSpeciesDetailsUseCase(private val pokeApiClient: PokeApiClient) {

    suspend operator fun invoke(id: String): Either<Error, PokemonSpeciesDetails> = either {
        val species = pokeApiClient.pokemonSpecies.get(name = id).bind { Error }
        val defaultVariety = pokeApiClient.pokemon.get(species.varieties.first { it.isDefault })
            .bind { Error }

        PokemonSpeciesDetails(
            localizedNames = species.names.associate { name ->
                name.language.asLanguage() to name.name
            },
            primaryVariety = PokemonDetails(
                types = defaultVariety.types.map { it.type.asType() },
                sprites = PokemonSprites(
                    default = defaultVariety.sprites.frontDefault ?: ""
                )
            )
        )
    }

    object Error
}