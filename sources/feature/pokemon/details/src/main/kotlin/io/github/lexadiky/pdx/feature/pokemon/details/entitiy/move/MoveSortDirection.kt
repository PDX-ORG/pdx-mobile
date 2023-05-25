package io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move

import io.github.lexadiky.pdx.feature.pokemon.details.R
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from

enum class MoveSortDirection (
    private val titleRes: Int
) {
    ASCENDING(R.string.feature_pokemon_move_sort_type_ascending),
    DESCENDING(R.string.feature_pokemon_move_sort_type_descending);

    val title: StringResource get() = StringResource.from(titleRes)
}
