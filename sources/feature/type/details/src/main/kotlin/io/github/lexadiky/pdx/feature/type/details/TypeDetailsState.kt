package io.github.lexadiky.pdx.feature.type.details

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelation
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelationTable
import io.github.lexadiky.pdx.feature.type.details.entity.TypePokemonPreview
import io.github.lexadiky.pdx.library.errorhandler.UIError

internal data class TypeDetailsState(
    val type: PokemonType,
    val damageRelations: PokemonTypeDamageRelation? = null,
    val featuredPokemon: List<TypePokemonPreview> = emptyList(),
    val error: UIError? = null
) {

    val damageTable: PokemonTypeDamageRelationTable? = damageRelations?.asTable()

    companion object {

        fun from(typeId: String): TypeDetailsState {
            return TypeDetailsState(
                type = PokemonType.values().first { it.id == typeId }
            )
        }
    }
}
