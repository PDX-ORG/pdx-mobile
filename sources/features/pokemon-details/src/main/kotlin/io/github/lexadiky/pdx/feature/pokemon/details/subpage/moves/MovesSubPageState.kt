package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves

import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveFilter
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSort
import io.github.lexadiky.pdx.lib.core.lce.DynamicLceList
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

data class MovesSubPageState(
    val movesRaw: DynamicLceList<*, PokemonMoveData> = emptyFlow(),
    val sortStrategy: MoveSort = MoveSort(),
    val error: UIError? = null,
    val filter: MoveFilter = MoveFilter()
) {
    val moves = movesRaw
        .flowOn(Dispatchers.IO)
        .map { sortStrategy.apply(it) }
        .map { filter.apply(it) }
}