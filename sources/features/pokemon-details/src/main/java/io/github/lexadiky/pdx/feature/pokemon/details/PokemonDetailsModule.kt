package io.github.lexadiky.pdx.feature.pokemon.details

import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats.StatsSubPageViewModel
import io.github.lexadiky.pdx.feature.pokemon.details.usecase.GetAvailableDetailsSections

internal val PokemonDetailsModule = eagerModule("pokemon-details") {
    import(PokemonDomainModule)
    internal {
        single { GetAvailableDetailsSections() }
        singleViewModel { args -> PokemonDetailsStyleFastFetchViewModel(pokemonId = args.get(), inject()) }
        singleViewModel { args -> PokemonDetailsViewModel(pokemonId = args.get(), inject(), inject(), inject(), inject()) }
        singleViewModel { args -> StatsSubPageViewModel(args.get(), args.get(), inject(), inject()) }
    }
}
