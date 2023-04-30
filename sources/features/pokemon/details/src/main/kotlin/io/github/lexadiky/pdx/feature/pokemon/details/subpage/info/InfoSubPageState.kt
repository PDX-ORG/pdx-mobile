package io.github.lexadiky.pdx.feature.pokemon.details.subpage.info

import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonDescriptionData
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonPhysicalDimension
import io.github.lexadiky.pdx.lib.core.error.GenericError
import io.github.lexadiky.pdx.lib.core.lce.DynamicLceList
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import kotlinx.coroutines.flow.emptyFlow

internal data class InfoSubPageState(
    val descriptions: DynamicLceList<GenericError, PokemonDescriptionData> = emptyFlow(),
    val dimensions: List<PokemonPhysicalDimension> = emptyList(),
    val error: UIError? = null
)
