package io.github.lexadiky.pdx.domain.pokemon.entity

data class EvolutionNode(
    val from: Variation?,
    val to: List<Variation>
) {

    data class Variation(
        val species: PokemonPreview,
    )
}
