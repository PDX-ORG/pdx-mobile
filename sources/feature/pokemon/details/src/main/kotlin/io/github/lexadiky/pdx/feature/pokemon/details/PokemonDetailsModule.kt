package io.github.lexadiky.pdx.feature.pokemon.details

import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.EvolutionSubPageSocket
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.info.InfoSubPageViewModel
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves.MovesSubPageSocket
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves.sort.MoveSortWidgetViewModel
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats.StatsSubPageSocket
import io.github.lexadiky.pdx.feature.pokemon.details.usecase.GetAvailableDetailsSections

internal val PokemonDetailsModule = eagerModule("pokemon-details") {
    import(PokemonDomainModule)
    internal {
        single { GetAvailableDetailsSections() }
        internal {
            singleViewModel { args -> PokemonDetailsStyleFastFetchSocket(pokemonId = args.get(), inject()) }
            singleViewModel { args -> PokemonDetailsSocket(pokemonId = args.get(), inject(), inject(), inject(), inject(), inject(), inject(), inject(), inject()) }
            singleViewModel { args -> StatsSubPageSocket(args.get(), inject(), inject()) }
            singleViewModel { args -> InfoSubPageViewModel(args.get(), args.get(), inject()) }
            singleViewModel { args -> MovesSubPageSocket(args.get(), inject(), inject()) }
            singleViewModel { args -> EvolutionSubPageSocket(args.get(), args.get(), inject(), inject()) }
            singleViewModel { MoveSortWidgetViewModel() }
        }
    }
}
