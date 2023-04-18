package io.github.lexadiky.pdx.domain.pokemon.repository

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.lib.fs.FsManager

internal class FavoritePokemonRepository(fsManager: FsManager) {

    private var savedPokemon by fsManager.atomic("favorite_pokemon")
        .stringSet("names", emptySet())

    private var savedSpecies by fsManager.atomic("favorite_species")
        .stringSet("names", emptySet())

    suspend fun save(pokemon: PokemonDetails) {
        savedPokemon = savedPokemon + pokemon.name
    }

    suspend fun save(pokemon: PokemonSpeciesDetails) {
        savedSpecies = savedSpecies + pokemon.name
    }

    suspend fun remove(pokemon: PokemonDetails) {
        savedPokemon = savedPokemon - pokemon.name
    }

    suspend fun remove(pokemon: PokemonSpeciesDetails) {
        savedSpecies = savedSpecies - pokemon.name
    }


    suspend fun isFavorite(pokemon: PokemonDetails): Boolean {
        return pokemon.name in savedPokemon
    }

    suspend fun isFavorite(pokemon: PokemonPreview): Boolean {
        return pokemon.name in savedSpecies
    }
}