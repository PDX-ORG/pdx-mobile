package io.github.lexadiky.pdx.feature.typechart

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.feature.typechart.chart.TypeChartSocket
import io.github.lexadiky.pdx.feature.typechart.chart.TypeChartSocketImpl
import io.github.lexadiky.pdx.feature.typechart.entity.PokemonSuggester
import io.github.lexadiky.pdx.feature.typechart.search.TypeSearchViewModel

val TypeModule by module("type") {
    import(PokemonDomainModule)

    internal {
        singleViewModel<TypeChartSocket> { TypeChartSocketImpl(inject(), inject()) }
        singleViewModel { TypeSearchViewModel(inject(), inject(), inject(), inject(), inject()) }
        single { PokemonSuggester() }
    }
}
