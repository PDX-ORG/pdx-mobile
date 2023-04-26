package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves.sort

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSort
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSortBuilder
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSortDirection
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSortDirectionBuilder
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSortType
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSortTypeBuilder

internal class MoveSortWidgetViewModel : ViewModel() {

    var state by mutableStateOf(MoveSortWidgetState())
        private set

    fun dismissBuild() {
        state = state.copy(builder = null)
    }

    fun openBuild() {
        state = state.copy(
            builder = MoveSortBuilder.initial()
        )
    }

    fun selectType(moveSortType: MoveSortType) {
        state = if (moveSortType == MoveSortType.Default) {
            state.copy(sort = MoveSort(), builder = null)
        } else {
            state.copy(
                builder = (state.builder as? MoveSortTypeBuilder)
                    ?.select(moveSortType)
            )
        }
    }

    fun selectDirection(moveSortDirection: MoveSortDirection) {
        state = state.copy(
            sort = (state.builder as? MoveSortDirectionBuilder)
                ?.select(moveSortDirection)
                ?: MoveSort(),
            builder = null
        )
    }
}