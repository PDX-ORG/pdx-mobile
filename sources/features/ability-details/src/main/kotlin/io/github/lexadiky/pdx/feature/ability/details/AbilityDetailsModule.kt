package io.github.lexadiky.pdx.feature.ability.details

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule

val AbilityDetailsModule by module("feature-ability-details") {
    import(PokemonDomainModule)
    internal {
        singleViewModel { param -> AbilityDetailsViewModel(param.get(), inject()) }
    }
}
