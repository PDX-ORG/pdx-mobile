package io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move

import io.github.lexadiky.pdx.feature.pokemon.details.R
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from

enum class MoveSortType(
    private val titleRes: Int
) {
    Default(R.string.feature_pokemon_move_sort_by_default),
    Name(R.string.feature_pokemon_move_sort_by_name),
    Type(R.string.feature_pokemon_move_sort_by_type),
    PP(R.string.feature_pokemon_move_sort_by_pp),
    Power(R.string.feature_pokemon_move_sort_by_power),
    Accuracy(R.string.feature_pokemon_move_sort_by_accuracy);

    val title: StringResource get() = StringResource.from(titleRes)
}
