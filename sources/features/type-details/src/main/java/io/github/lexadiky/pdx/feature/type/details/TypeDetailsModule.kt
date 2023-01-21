package io.github.lexadiky.pdx.feature.type.details

import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.lib.arc.di.module

val TypeDetailsModule by module("type-details") {
    import(PokemonDomainModule)
    internal {
        viewModel { arg -> TypeDetailsViewModel(arg.get(), inject(), inject(), inject()) }
    }
}