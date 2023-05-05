package io.github.lexadiky.pdx.feature.pokemon.details

import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.EvolutionSubPageViewModel
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.info.InfoSubPageViewModel
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves.MovesSubPageViewModel
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves.sort.MoveSortWidgetViewModel
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats.StatsSubPageViewModel
import io.github.lexadiky.pdx.feature.pokemon.details.usecase.GetAvailableDetailsSections

internal val PokemonDetailsModule = eagerModule("pokemon-details") {
    import(PokemonDomainModule)
    internal {
        single { GetAvailableDetailsSections() }
        internal {
            singleViewModel { args -> PokemonDetailsStyleFastFetchViewModel(pokemonId = args.get(), inject()) }
            singleViewModel { args -> PokemonDetailsViewModel(pokemonId = args.get(), inject(), inject(), inject(), inject(), inject(), inject(), inject()) }
            singleViewModel { args -> StatsSubPageViewModel(args.get(), inject(), inject()) }
            singleViewModel { args -> InfoSubPageViewModel(args.get(), args.get(), inject()) }
            singleViewModel { args -> MovesSubPageViewModel(args.get(), inject(), inject()) }
            singleViewModel { args -> EvolutionSubPageViewModel(args.get(), args.get()) }
            singleViewModel { MoveSortWidgetViewModel() }
        }
    }
}
