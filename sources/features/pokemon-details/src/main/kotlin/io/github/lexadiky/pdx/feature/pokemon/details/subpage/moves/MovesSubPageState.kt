package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves

import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.lib.core.lce.Lce
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovesSubPageState(
    val moves: Flow<List<Lce<*, PokemonMoveData>>> = emptyFlow(),
    val error: UIError? = null
)