package io.github.lexadiky.pdx.feature.pokemon.details.subpage.info

import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonDescriptionData
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonPhysicalDimension
import io.github.lexadiky.pdx.library.core.lce.Lce
import io.github.lexadiky.pdx.library.errorhandler.UIError
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class InfoSubPageState(
    val descriptions: ImmutableList<Lce<*, PokemonDescriptionData>> = persistentListOf(),
    val dimensions: List<PokemonPhysicalDimension> = emptyList(),
    val error: UIError? = null
)
