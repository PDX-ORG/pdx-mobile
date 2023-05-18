package io.github.lexadiky.pdx.domain.pokemon.usecase.favorite

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.repository.FavoritePokemonRepository

class IsPokemonFavoriteUseCase internal constructor(private val repository: FavoritePokemonRepository) {

    suspend operator fun invoke(pokemonDetails: PokemonDetails): Boolean {
        return repository.isFavorite(pokemonDetails)
    }

    suspend operator fun invoke(pokemon: PokemonPreview): Boolean {
        return repository.isFavorite(pokemon)
    }
}
