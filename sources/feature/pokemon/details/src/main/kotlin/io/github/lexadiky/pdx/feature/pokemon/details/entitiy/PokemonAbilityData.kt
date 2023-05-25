package io.github.lexadiky.pdx.feature.pokemon.details.entitiy

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonAbility
import io.github.lexadiky.pdx.feature.pokemon.details.R
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from

class PokemonAbilityData(
    val name: String,
    val localeName: StringResource,
    val type: StringResource
) {

    companion object {

        fun from(ability: PokemonAbility) = PokemonAbilityData(
            name = ability.name,
            localeName = StringResource.from(ability.nameLocale),
            type = StringResource.from(
                if (ability.isHidden) {
                    R.string.feature_pokemon_details_section_ability_type_hidden
                } else {
                    R.string.feature_pokemon_details_section_ability_type_normal
                }
            )
        )
    }
}
