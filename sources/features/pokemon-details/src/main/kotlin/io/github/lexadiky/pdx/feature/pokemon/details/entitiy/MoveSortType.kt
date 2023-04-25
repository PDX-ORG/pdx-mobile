package io.github.lexadiky.pdx.feature.pokemon.details.entitiy

import io.github.lexadiky.pdx.feature.pokemon.details.R
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from

enum class MoveSortType(
    private val titleRes: Int
) {
    Default(R.string.feature_pokemon_move_sort_by_default),
    Name(R.string.feature_pokemon_move_sort_by_name),
    Type(R.string.feature_pokemon_move_sort_by_type),
    PP(R.string.feature_pokemon_move_sort_by_pp);

    val title: StringResource get() = StringResource.from(titleRes)
}
