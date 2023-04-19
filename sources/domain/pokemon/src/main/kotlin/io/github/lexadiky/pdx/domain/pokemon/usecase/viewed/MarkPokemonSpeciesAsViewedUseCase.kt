package io.github.lexadiky.pdx.domain.pokemon.usecase.viewed

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.repository.ViewedPokemonRepository

class MarkPokemonSpeciesAsViewedUseCase internal constructor(
    private val repository: ViewedPokemonRepository
) {

    suspend operator fun invoke(pokemon: PokemonSpeciesDetails) = repository
        .saveLatest(pokemon)
        .mapLeft { Error }

    object Error
}
