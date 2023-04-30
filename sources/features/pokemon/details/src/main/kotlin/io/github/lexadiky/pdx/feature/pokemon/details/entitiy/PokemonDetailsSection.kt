package io.github.lexadiky.pdx.feature.pokemon.details.entitiy

import io.github.lexadiky.pdx.feature.pokemon.details.R
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from

internal enum class PokemonDetailsSection(private val titleResource: Int) {
    Stats(R.string.feature_pokemon_details_section_title_stats),
    Info(R.string.feature_pokemon_details_section_title_info),
    Evolution(R.string.feature_pokemon_details_section_title_evolution),
    Battle(R.string.feature_pokemon_details_section_title_battle);

    val title: StringResource = StringResource.from(titleResource)
}
