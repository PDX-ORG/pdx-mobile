package io.github.lexadiky.pdx.feature.typechart

import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.feature.typechart.chart.TypeChartViewModel
import io.github.lexadiky.pdx.feature.typechart.entity.PokemonSuggester
import io.github.lexadiky.pdx.feature.typechart.search.TypeSearchViewModel
import io.github.lexadiky.akore.alice.module

val TypeModule by module("type") {
    import(PokemonDomainModule)

    internal {
        viewModel { TypeChartViewModel(inject(), inject()) }
        viewModel { TypeSearchViewModel(inject(), inject(), inject(), inject(), inject()) }
        single { PokemonSuggester() }
    }
}