package io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.lib.core.lce.Lce
import io.github.lexadiky.pdx.lib.core.lce.contentOrNull

data class MoveSort(
    val type: MoveSortType = MoveSortType.Default,
    val direction: MoveSortDirection = MoveSortDirection.ASCENDING
) {
    fun apply(items: List<Lce<*, PokemonMoveData>>): List<Lce<*, PokemonMoveData>> {
        val baseComparator = type.asComparator() ?: return items

        val comparator = baseComparator.thenComparing { arg ->
            arg.contentOrNull()?.name.orEmpty()
        }

        return when (direction) {
            MoveSortDirection.ASCENDING -> items.sortedWith(comparator)
            MoveSortDirection.DESCENDING -> items.sortedWith(comparator).reversed()
        }
    }

    private fun MoveSortType.asComparator(): Comparator<Lce<*, PokemonMoveData>>? = when (type) {
        MoveSortType.Default -> null
        MoveSortType.Name -> Comparator.comparing { it.contentOrNull()?.name ?: "" }
        MoveSortType.Type -> Comparator.comparing {  it.contentOrNull()?.type ?: PokemonType.BUG }
        MoveSortType.PP -> Comparator.comparing {  it.contentOrNull()?.pp ?: 0 }
        MoveSortType.Power -> Comparator.comparing {  it.contentOrNull()?.power ?: 0 }
        MoveSortType.Accuracy -> Comparator.comparing {  it.contentOrNull()?.accuracy ?: Int.MAX_VALUE }
    }
}
