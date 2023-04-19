package io.github.lexadiky.pdx.feature.home

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.generated.analytics.HomeEventsSpec

val HomePageModule by module("feature-home-page") {
    import(PokemonDomainModule)
    internal {
        single { HomeEventsSpec(inject()) }
        singleViewModel { HomePageViewModel(inject(), inject(), inject(), inject(), inject()) }
    }
}
