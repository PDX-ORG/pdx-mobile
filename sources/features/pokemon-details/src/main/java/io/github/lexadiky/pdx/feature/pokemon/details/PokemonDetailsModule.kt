package io.github.lexadiky.pdx.feature.pokemon.details

import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule

internal val PokemonDetailsModule = eagerModule("pokemon-details") {
    import(PokemonDomainModule)
    internal {
        viewModel { args -> PokemonDetailsViewModel(pokemonId = args.get(), inject(), inject()) }
    }
}
