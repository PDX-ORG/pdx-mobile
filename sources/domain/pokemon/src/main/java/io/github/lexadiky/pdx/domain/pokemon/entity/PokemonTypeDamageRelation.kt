package io.github.lexadiky.pdx.domain.pokemon.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeDamageRelation(
    @SerialName("double_from")
    val doubleDamageFrom: List<PokemonType>,
    @SerialName("double_to")
    val doubleDamageTo: List<PokemonType>,
    @SerialName("half_from")
    val halfDamageFrom: List<PokemonType>,
    @SerialName("half_to")
    val halfDamageTo: List<PokemonType>,
    @SerialName("zero_from")
    val zeroDamageFrom: List<PokemonType>,
    @SerialName("zero_to")
    val zeroDamageTo: List<PokemonType>
) {

    fun asTable(): PokemonTypeDamageRelationTable =
        PokemonTypeDamageRelationTable(from = calculateFromTable(), to = calculateToTable())

    private fun calculateFromTable(): Map<PokemonType, Float> {
        return doubleDamageFrom.associateWith { DAMAGE_MOD_DOUBLE } +
            halfDamageFrom.associateWith { DAMAGE_MOD_HALF } +
            zeroDamageFrom.associateWith { DAMAGE_MOD_ZERO }
    }

    private fun calculateToTable(): Map<PokemonType, Float> {
        return doubleDamageTo.associateWith { 2f } +
            halfDamageTo.associateWith { DAMAGE_MOD_HALF } +
            zeroDamageTo.associateWith { DAMAGE_MOD_ZERO }
    }

    companion object {

        private const val DAMAGE_MOD_DOUBLE = 2.0f
        private const val DAMAGE_MOD_HALF = 0.5f
        private const val DAMAGE_MOD_ZERO = 0f
    }
}
