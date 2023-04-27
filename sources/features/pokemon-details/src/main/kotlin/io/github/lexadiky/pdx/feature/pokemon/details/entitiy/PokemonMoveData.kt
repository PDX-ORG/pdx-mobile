package io.github.lexadiky.pdx.feature.pokemon.details.entitiy

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.lib.core.fts.FtsIndex
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from

data class PokemonMoveData(
    val name: String,
    val localeName: StringResource,
    val localeFlavourText: StringResource,
    val type: PokemonType,
    val pp: Int?,
    val power: Int?,
    val accuracy: Int?,
    val ftsIndex: FtsIndex
) {

    val ppLabel: StringResource = StringResource.from(pp?.toString().orEmpty())
    val powerLabel: StringResource = StringResource.from(power?.toString().orEmpty())
    val accuracyLabel: StringResource = StringResource.from(accuracy?.toString().orEmpty())
}
