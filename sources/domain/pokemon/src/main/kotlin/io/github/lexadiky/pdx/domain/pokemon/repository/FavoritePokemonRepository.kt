package io.github.lexadiky.pdx.domain.pokemon.repository

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.lib.microdata.MicrodataManager

internal class FavoritePokemonRepository(microdataManager: MicrodataManager) {

    private val microdata = microdataManager.acquire(this, "favorite_pokemon")

    private val savedPokemon = microdata.strings("favorite_pokemon_names")
    private val savedSpecies = microdata.strings("favorite_species_names")

    suspend fun save(pokemon: PokemonDetails) {
        savedPokemon.set(savedPokemon.get().orEmpty() + pokemon.name)
    }

    suspend fun save(pokemon: PokemonSpeciesDetails) {
        savedSpecies.set(savedPokemon.get().orEmpty() + pokemon.name)
    }

    suspend fun remove(pokemon: PokemonDetails) {
        savedPokemon.set(savedPokemon.get().orEmpty() - pokemon.name)
    }

    suspend fun remove(pokemon: PokemonSpeciesDetails) {
        savedSpecies.set(savedPokemon.get().orEmpty() - pokemon.name)
    }


    suspend fun isFavorite(pokemon: PokemonDetails): Boolean {
        return pokemon.name in savedPokemon.get().orEmpty()
    }

    suspend fun isFavorite(pokemon: PokemonPreview): Boolean {
        return pokemon.name in savedSpecies.get().orEmpty()
    }
}
