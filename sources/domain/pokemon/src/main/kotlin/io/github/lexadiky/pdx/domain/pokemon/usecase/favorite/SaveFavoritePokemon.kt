package io.github.lexadiky.pdx.domain.pokemon.usecase.favorite

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.repository.FavoritePokemonRepository

class SaveFavoritePokemon internal constructor(private val repository: FavoritePokemonRepository) {

    suspend operator fun invoke(pokemonSpeciesDetails: PokemonSpeciesDetails, pokemonDetails: PokemonDetails, isFavorite: Boolean) {
        if (isFavorite) {
            repository.save(pokemonDetails)
            repository.save(pokemonSpeciesDetails)
        } else {
            repository.remove(pokemonDetails)
            repository.remove(pokemonSpeciesDetails)
        }
    }
}
