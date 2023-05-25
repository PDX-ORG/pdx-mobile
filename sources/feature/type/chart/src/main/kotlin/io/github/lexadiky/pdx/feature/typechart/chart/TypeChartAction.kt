package io.github.lexadiky.pdx.feature.typechart.chart

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType

sealed interface TypeChartAction {

    class TypeClicked(val type: PokemonType) : TypeChartAction

    object HideError : TypeChartAction

    sealed interface Navigate : TypeChartAction {

        class TypeDetails(val type: PokemonType) : Navigate
    }
}
