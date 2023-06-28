package io.github.lexadiky.pdx.domain.pokemon.entity.evolution

sealed interface EvolutionMethod {

    object Unknown : EvolutionMethod

    data class LevelUp(val atLevel: Int) : EvolutionMethod

    data class Or(val methods: List<EvolutionMethod>) : EvolutionMethod

    data class And(val methods: List<EvolutionMethod>) : EvolutionMethod
}
