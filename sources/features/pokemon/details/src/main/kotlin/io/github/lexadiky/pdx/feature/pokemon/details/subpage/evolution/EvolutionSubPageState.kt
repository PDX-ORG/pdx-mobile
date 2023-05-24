package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution

import io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.entity.EvolutionLinkPokemonVR
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.entity.EvolvesToVR
import io.github.lexadiky.pdx.library.errorhandler.UIError

internal data class EvolutionSubPageState(
    val error: UIError? = null,
    val evolvesFrom: EvolvesToVR? = null,
    val current: EvolutionLinkPokemonVR? = null,
    val evolvesTo: List<EvolvesToVR> = emptyList()
) {

    val isSingleToSpecies = evolvesTo.size == 1
}
