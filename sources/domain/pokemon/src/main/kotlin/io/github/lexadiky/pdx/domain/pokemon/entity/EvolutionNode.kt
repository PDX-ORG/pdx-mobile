package io.github.lexadiky.pdx.domain.pokemon.entity

import io.github.lexadiky.pdx.domain.pokemon.entity.evolution.EvolutionMethod

data class EvolutionNode(
    val from: Variation?,
    val current: PokemonPreview,
    val to: List<Variation>
) {

    data class Variation(
        val species: PokemonPreview,
        val method: EvolutionMethod
    )
}
