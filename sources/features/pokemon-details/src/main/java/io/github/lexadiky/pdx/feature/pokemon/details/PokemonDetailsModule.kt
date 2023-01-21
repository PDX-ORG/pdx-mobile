package io.github.lexadiky.pdx.feature.pokemon.details

import androidx.compose.runtime.Composable
import io.github.lexadiky.pdx.lib.arc.di.eagerModule

internal val PokemonDetailsModule = eagerModule("pokemon-details") {
    internal {
        viewModel { args -> PokemonDetailsViewModel(pokemonId = args.get()) }
    }
}
