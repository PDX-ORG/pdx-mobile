package io.github.lexadiky.pdx.feature.pokemon.details

import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.akore.alice.eagerModule

internal val PokemonDetailsModule = eagerModule("pokemon-details") {
    internal {
        viewModel { args -> PokemonDetailsViewModel(pokemonId = args.get()) }
    }
}
