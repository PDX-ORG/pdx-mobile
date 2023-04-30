package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves.sort

import io.github.lexadiky.pdx.feature.pokemon.details.R
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSort
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSortBuilder
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSortType
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.format
import io.github.lexadiky.pdx.lib.resources.string.from

data class MoveSortWidgetState(
    val sort: MoveSort = MoveSort(),
    val builder: MoveSortBuilder? = null,
) {

    val title: StringResource = if (sort.type == MoveSortType.Default) {
        StringResource.from(R.string.feature_pokemon_move_sort_state_default)
    } else {
        StringResource.from(R.string.feature_pokemon_move_sort_state_selected)
            .format(sort.type.title, sort.direction.title)
    }

    val isMenuOpen = builder != null

    val isSet = sort != MoveSort()
}
