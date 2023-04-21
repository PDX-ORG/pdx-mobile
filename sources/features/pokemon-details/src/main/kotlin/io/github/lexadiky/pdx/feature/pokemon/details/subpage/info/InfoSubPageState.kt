package io.github.lexadiky.pdx.feature.pokemon.details.subpage.info

import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonDescriptionData
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonPhysicalDimension
import io.github.lexadiky.pdx.lib.errorhandler.UIError

internal data class InfoSubPageState(
    val descriptions: List<PokemonDescriptionData> = emptyList(),
    val dimensions: List<PokemonPhysicalDimension> = emptyList(),
    val error: UIError? = null
)
