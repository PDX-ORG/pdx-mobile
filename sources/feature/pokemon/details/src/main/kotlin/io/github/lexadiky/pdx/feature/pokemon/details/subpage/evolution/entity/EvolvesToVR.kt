package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.entity

import io.github.lexadiky.pdx.domain.pokemon.entity.evolution.EvolutionMethod

internal data class EvolvesToVR(
    val pokemon: EvolutionLinkPokemonVR,
    val method: EvolutionMethod
)
