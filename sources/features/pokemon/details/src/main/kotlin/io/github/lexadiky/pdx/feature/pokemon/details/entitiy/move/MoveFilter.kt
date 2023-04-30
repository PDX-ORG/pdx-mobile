package io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move

import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.lib.core.fts.matchesOrEmpty
import io.github.lexadiky.pdx.lib.core.lce.Lce
import io.github.lexadiky.pdx.lib.core.lce.filterLce

data class MoveFilter(val query: String = "") {

    fun apply(items: List<Lce<*, PokemonMoveData>>): List<Lce<*, PokemonMoveData>> {
        return items.filterLce { move ->
            move.ftsIndex.matchesOrEmpty(query)
        }
    }
}
