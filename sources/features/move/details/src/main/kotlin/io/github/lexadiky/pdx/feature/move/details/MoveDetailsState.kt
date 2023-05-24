package io.github.lexadiky.pdx.feature.move.details

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.move.details.entity.attribute.MoveAttribute
import io.github.lexadiky.pdx.library.errorhandler.UIError
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from

data class MoveDetailsState(
    val localeName: StringResource? = null,
    val localeFlavourText: StringResource? = null,
    val localeShortEffect: StringResource? = null,
    val attributes: List<MoveAttribute> = emptyList(),
    val type: PokemonType? = null,
    val error: UIError? = null,
    val pp: Int? = null
) {

    val ppLabel = StringResource.from(pp?.toString().orEmpty())

    val isLoading: Boolean = localeName == null
}
