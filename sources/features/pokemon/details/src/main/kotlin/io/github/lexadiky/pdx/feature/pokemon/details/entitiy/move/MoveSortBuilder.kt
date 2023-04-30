package io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move

sealed interface MoveSortBuilder {

    companion object {

        fun initial(): MoveSortTypeBuilder = MoveSortTypeBuilder(
            availableTypes = MoveSortType.values().toList()
        )
    }
}

data class MoveSortTypeBuilder(
    val availableTypes: List<MoveSortType>
) : MoveSortBuilder {

    fun select(type: MoveSortType) = MoveSortDirectionBuilder(
        selectedType = type,
        availableDirection = MoveSortDirection.values().toList()
    )
}

data class MoveSortDirectionBuilder(
    val selectedType: MoveSortType,
    val availableDirection: List<MoveSortDirection>
) : MoveSortBuilder {

    fun select(direction: MoveSortDirection): MoveSort = MoveSort(
        type = selectedType,
        direction = direction
    )
}
