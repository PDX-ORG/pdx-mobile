package io.github.lexadiky.pdx.feature.whois

import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.achievement.AchievementModule
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.akore.alice.module

val WhoIsModule by module("who-is") {
    import(PokemonDomainModule)
    import(AchievementModule)

    internal {
        viewModel { WhoIsViewModel(inject(), inject(), inject()) }
    }
}