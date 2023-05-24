package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves

import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveFilter
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSort
import io.github.lexadiky.pdx.library.core.lce.Lce
import io.github.lexadiky.pdx.library.errorhandler.UIError
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class MovesSubPageState(
    private val movesRaw: ImmutableList<Lce<*, PokemonMoveData>> = persistentListOf(),
    val sortStrategy: MoveSort = MoveSort(),
    val error: UIError? = null,
    val filter: MoveFilter = MoveFilter()
) {
    val moves = movesRaw.let(sortStrategy::apply)
        .let(filter::apply)
}
