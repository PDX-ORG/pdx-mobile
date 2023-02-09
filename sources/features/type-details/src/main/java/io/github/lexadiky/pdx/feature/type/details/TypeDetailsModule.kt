package io.github.lexadiky.pdx.feature.type.details

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule

val TypeDetailsModule by module("type-details") {
    import(PokemonDomainModule)
    internal {
        viewModel { arg -> TypeDetailsViewModel(arg.get(), inject(), inject(), inject()) }
    }
}
