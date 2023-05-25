package io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move

import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.library.core.fts.matchesOrEmpty
import io.github.lexadiky.pdx.library.core.lce.Lce
import io.github.lexadiky.pdx.library.core.lce.filterLce
import kotlinx.collections.immutable.ImmutableList

data class MoveFilter(val query: String = "") {

    fun apply(items: ImmutableList<Lce<*, PokemonMoveData>>): ImmutableList<Lce<*, PokemonMoveData>> {
        return items.filterLce { move ->
            move.ftsIndex.matchesOrEmpty(query)
        }
    }
}
