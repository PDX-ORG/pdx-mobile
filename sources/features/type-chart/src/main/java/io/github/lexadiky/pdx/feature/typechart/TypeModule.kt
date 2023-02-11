package io.github.lexadiky.pdx.feature.typechart

import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.feature.typechart.chart.TypeChartViewModel
import io.github.lexadiky.pdx.feature.typechart.entity.PokemonSuggester
import io.github.lexadiky.pdx.feature.typechart.search.TypeSearchViewModel
import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel

val TypeModule by module("type") {
    import(PokemonDomainModule)

    internal {
        singleViewModel { TypeChartViewModel(inject(), inject()) }
        singleViewModel { TypeSearchViewModel(inject(), inject(), inject(), inject(), inject()) }
        single { PokemonSuggester() }
    }
}