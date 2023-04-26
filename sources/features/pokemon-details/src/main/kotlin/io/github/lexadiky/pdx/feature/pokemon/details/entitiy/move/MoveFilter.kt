package io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move

import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.lib.core.lce.Lce
import io.github.lexadiky.pdx.lib.core.lce.filterLce

data class MoveFilter(val query: String = "") {

    fun apply(items: List<Lce<*, PokemonMoveData>>): List<Lce<*, PokemonMoveData>> {
        val sanitizedQuery = query.lowercase()

        return items.filterLce {
            sanitizedQuery in it.name.lowercase()
        }
    }
}
