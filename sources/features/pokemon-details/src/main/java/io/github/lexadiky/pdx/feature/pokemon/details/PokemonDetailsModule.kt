package io.github.lexadiky.pdx.feature.pokemon.details

import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats.StatsSubPageViewModel
import io.github.lexadiky.pdx.feature.pokemon.details.usecase.GetAvailableDetailsSections

internal val PokemonDetailsModule = eagerModule("pokemon-details") {
    import(PokemonDomainModule)
    internal {
        single { GetAvailableDetailsSections() }
        viewModel { args -> PokemonDetailsStyleFastFetchViewModel(pokemonId = args.get(), inject()) }
        viewModel { args -> PokemonDetailsViewModel(pokemonId = args.get(), inject(), inject(), inject(), inject()) }
        viewModel { args -> StatsSubPageViewModel(args.get(), args.get()) }
    }
}
