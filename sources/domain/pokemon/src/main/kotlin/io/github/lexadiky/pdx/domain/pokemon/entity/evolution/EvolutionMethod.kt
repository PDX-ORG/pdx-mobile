package io.github.lexadiky.pdx.domain.pokemon.entity.evolution

sealed interface EvolutionMethod {

    object Unknown : EvolutionMethod

    data class LevelUp(val atLevel: Int) : EvolutionMethod
}
