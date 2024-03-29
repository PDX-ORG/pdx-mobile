package io.github.lexadiky.pdx.feature.pokemon.list.usecase

import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListNavigator
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericListItem
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.nibbler.navigate

class PokemonGenericListNavigator(
    private val navigator: Navigator
) : GenericListNavigator<PokemonGenericListItem> {

    override suspend fun navigateToDetails(item: PokemonGenericListItem) {
        navigator.navigate("pdx://pokemon/${item.id}")
    }

    override suspend fun navigateToTag(item: PokemonGenericListItem, tag: GenericListItem.Tag) {
        navigator.navigate("pdx://type/${tag.id}")
    }
}
