package io.github.lexadiky.pdx.feature.pokemon.details.entitiy

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.lib.resources.string.StringResource

data class PokemonMoveData(
    val name: String,
    val localeName: StringResource,
    val localeFlavourText: StringResource,
    val type: PokemonType,
    val ppLabel: StringResource,
    val ppValue: Int?
)
