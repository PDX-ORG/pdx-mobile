package io.github.lexadiky.pdx.feature.home

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule

val HomePageModule by module("feature-home-page") {
    import(PokemonDomainModule)
    internal {
        singleViewModel { HomePageViewModel(inject(), inject(), inject()) }
    }
}
