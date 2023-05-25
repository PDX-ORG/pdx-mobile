package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSort

sealed interface MovesSubPageAction {

    data class QueryUpdated(val query: String) : MovesSubPageAction

    data class SortUpdated(val sort: MoveSort) : MovesSubPageAction

    sealed interface Navigate : MovesSubPageAction {

        data class MoveDetails(val value: PokemonMoveData) : Navigate

        data class TypeDetails(val type: PokemonType) : Navigate
    }
}
